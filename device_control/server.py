import configparser
import json
import random
import time
import traceback

import pymysql
import requests
from sanic import Request, Sanic, response
from sanic.log import logger

config = configparser.ConfigParser()
config.read('server-config.ini')

db_config = {
    'host': config['database']['host'],
    'port': int(config['database']['port']),
    'user': config['database']['user'],
    'password': config['database']['password'],
    'db': config['database']['db']
}

biz_callback_url = config['biz']['callback_url']

# 创建 Sanic 应用
app = Sanic("Device")

# JSON 形式输出异常
app.config.FALLBACK_ERROR_FORMAT = "json"
SECRET_KEY = "my_secret_key"


@app.get("/devices", ignore_body=False)
async def get_devices(request):
    try:
        # 获取连接、游标
        connection = pymysql.connect(**db_config)
        cursor = connection.cursor()
        sql = """SELECT * FROM tbl_device"""
        # 解析传入的参数
        data = request.json
        ids = None
        if data:
            ids = data.get('ids', None)
            
        # 查询语句
        if ids:
            placeholder = ', '.join(['%s'] * len(ids))
            sql += f" WHERE id IN ({placeholder})"

        cursor.execute(sql, ids)
        results = cursor.fetchall()
        devices = []
        for row in results:
            # 设备信息 JSON 格式
            device = {
                "id": row[0],
                "name": row[1],
                "type": row[2],
                "sn": row[3],
                "passwd": row[4],
            }
            devices.append(device)

        connection.close()
        return response.json({
            "status": 1,
            "message": "获取设备信息成功！",
            "data": devices
        })
    except Exception as e:
        connection.close()
        return response.json(
            {
                "status": 0,
                "message": traceback.format_exc()
            }
        )


# 添加设备信息
@app.post("/devices")
async def add_devices(request: Request):
    try:
        # 打开连接
        connection = pymysql.connect(**db_config)
        devices = request.json

        with connection.cursor() as cursor:
            sql = "INSERT INTO tbl_device (id, name, type, sn, passwd) VALUES (%s, %s, %s, %s, %s)"
            for device in devices:
                id = str(int(time.time()) * 10000 +
                         random.randint(1000, 9999))  # 生成唯一的id
                name = device.get('name')
                type = device.get('type')
                sn = device.get('sn')
                passwd = device.get('passwd')
                cursor.execute(sql, (id, name, type, sn, passwd))

            connection.commit()

        connection.close()
        return response.json(
            {
                "status": 1,
                "message": "设备信息添加成功！"
            }
        )
    except Exception as e:
        connection.close()
        return response.json(
            {
                "status": 0,
                "message": traceback.format_exc()
            }
        )


# 修改设备信息
@app.put("/devices")
async def update_devices(request):
    connection = None
    try:
        # 打开连接
        connection = pymysql.connect(**db_config)
        # 解析传入的参数
        devices = request.json
        with connection.cursor() as cursor:
            sql = "UPDATE tbl_device SET name=%s, type=%s, sn=%s, passwd=%s WHERE id=%s"
            for device in devices:
                id = device.get('id')
                if not id:
                    raise Exception("设备 ID 不能为空")
                name = device.get('name')
                type = device.get('type')
                sn = device.get('sn')
                passwd = device.get('passwd')
                cursor.execute(sql, (name, type, sn, passwd, id))

            connection.commit()

        connection.close()
        return response.json(
            {
                "status": 1,
                "message": "设备信息更新成功！"
            }
        )
    except Exception as e:
        connection.close()
        return response.json(
            {
                "status": 0,
                "message": traceback.format_exc()
            }
        )

# 删除设备信息


@app.delete("/devices")
async def delete_devices(request):
    connection = None
    try:
        # 打开连接
        connection = pymysql.connect(**db_config)
        # 解析传入的参数
        ids = request.json.get('ids')

        # 更新数据库
        with connection.cursor() as cursor:
            placeholder = ', '.join(['%s'] * len(ids))
            sql = f"DELETE FROM tbl_device WHERE id IN ({placeholder})"
            cursor.execute(sql, ids)

            connection.commit()

        connection.close()
        return response.json(
            {
                "status": 1,
                "message": "设备信息删除成功！"
            }
        )
    except Exception as e:
        connection.close()
        return response.json(
            {
                "status": 0,
                "message": traceback.format_exc()
            }
        )


# 身份验证成功后的业务逻辑，解析客户端的请求，并将其分发给具体业务方法
async def ws_biz(request, ws):
    while True:
        try:
            data = await ws.recv()
            logger.info(f"[{request.ip}:{request.port}]: {data}")

            device_data = json.loads(data)
            sn = device_data.get("sn")
            temperature = device_data.get("temperature")
            humidity = device_data.get("humidity")

            report_data = {
                "sn": sn,
                "temperature": temperature,
                "humidity": humidity,
            }

            try:
                resp = requests.post(biz_callback_url, json=report_data)
                if resp.status_code == 200:
                    print("数据上报成功")
                else:
                    print("请求失败，状态码:", resp.status_code)
            except requests.exceptions.RequestException as e:
                print(f"请求过程中出现错误: {e}")

            await ws.send(data)

        except Exception as e:
            logger.info(str(e))
            break


@app.websocket("/devices/auth/ws")
async def ws_auth(request, ws):
    params = request.args
    sn = params.get("sn")
    passwd = params.get("passwd")
    connection = pymysql.connect(**db_config)
    sql = """SELECT * FROM tbl_device where sn = %s and passwd = %s"""
    cursor = connection.cursor()
    cursor.execute(sql, (sn, passwd))
    result = cursor.fetchone()
    connection.close()

    # request 对象中包含建立 WebSocket 连接时第一条 HTTP 消息中的所有数据，包括发起请求客户端的 IP、端口号等信息
    logger.info(f"/devices/ws/auth connected by {request.ip}:{request.port}")

    if result is None:
        logger.info(
            f"/devices/ws/auth disconnected by {request.ip}:{request.port}")
        await ws.close()
        return response.json(
            {
                "status": 0,
                "message": "身份验证失败"
            }
        )

    logger.info("身份验证成功")
    await ws.send("身份验证成功")
    await ws_biz(request, ws)

    return response.json(
        {
            "status": 1,
            "message": "身份验证成功！"
        }
    )


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=9000, single_process=True)
