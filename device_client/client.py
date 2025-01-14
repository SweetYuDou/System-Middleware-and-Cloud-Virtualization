"""
 运行示例： python client.py ws://127.0.0.1:8000/devices/auth/ws
"""
import ast
import configparser
import json
import logging
import random
import sys
import time

from websockets.sync.client import connect

# 将输出日志等级设置为 INFO 级别
logging.basicConfig(level=logging.INFO)

# 从命令行获取 WebSocket URL
ws_url = sys.argv[1]
sn = sys.argv[2]
passwd = sys.argv[3]


config = configparser.ConfigParser()
config.read('client-config.ini')
temperature_range = ast.literal_eval(config.get('config', 'temperature_range'))
humidity_range = ast.literal_eval(config.get('config', 'humidity_range'))
temperature_spike = config.getint('config', 'temperature_spike')
humidity_drop = config.getint('config', 'humidity_drop')
spike_interval = config.getint('config', 'spike_interval')
spike_countdown = config.getint('config', 'spike_countdown')
spike_timeout = config.getint('config', 'spike_timeout')

# 记录陡变发生的数据
spike_data = {
    "last_spike_time": time.time(),
    "spike_count": 0,
    "temperature_increase": 0.0,
    "humidity_decrease": 0.0
}

def generate_data():
    global spike_data
    current_time = time.time()

    # 检查是否需要重置陡变数据
    isNormal = random.random() > (spike_timeout / spike_interval) * spike_countdown

    if (current_time - spike_data["last_spike_time"]) > spike_interval:
        spike_data["temperature_increase"] = 0
        spike_data["humidity_decrease"] = 0
        spike_data["last_spike_time"] = current_time
        spike_data["spike_count"] = 0

    # 检查是否处于陡变期间
    if 0 <= spike_data["spike_count"] < spike_countdown or not isNormal:
        # 计算当前陡变的温度和湿度变化
        spike_data["temperature_increase"] = (temperature_spike / (spike_countdown - 1)) * spike_data[
            "spike_count"] + random.random() * 0.3
        spike_data["humidity_decrease"] = (humidity_drop / (spike_countdown - 1)) * spike_data[
            "spike_count"] + random.random() * 0.3
    else:
        spike_data["temperature_increase"] = 0
        spike_data["humidity_decrease"] = 0

    temperature = random.uniform(*temperature_range) + spike_data["temperature_increase"]
    humidity = random.uniform(*humidity_range) - spike_data["humidity_decrease"]

    # 只有在陡变期间才增加计数
    if not isNormal or spike_data["spike_count"] > 0:
        spike_data["spike_count"] += 1

    if spike_data["spike_count"] >= spike_countdown:
        # 重置计数器，为下一次陡变做准备
        spike_data["spike_count"] = 0

    return temperature, humidity


def echo_biz(ws_client):
    while True:
        temperature, humidity = generate_data()
        pdu = {
            "sn": sn,
            "temperature": round(temperature, 2),
            "humidity": round(humidity, 2),
        }
        ws_client.send(json.dumps(pdu))
        recv_data = ws_client.recv()
        print(f"RECV: {recv_data}")
        time.sleep(spike_timeout)  # 定时发送一次数据


ws_url = f"{ws_url}?sn={sn}&passwd={passwd}"

# 向服务器发起 WebSocket 连接
with connect(ws_url) as ws_client:
    logging.info(f"成功连接到 {ws_url}")
    # 连接建立后执行 echo_biz 简单回声业务函数
    echo_biz(ws_client)

logging.info("程序即将退出")
