from sanic import Sanic
from sanic.response import json
import csv
import os


DATA_FILE_NAME = "devices.csv"

app = Sanic("Hello")

app.static("/", "./static", name="static")
app.static("/", "./static/index.html", name="index")

# csv转dict
def csv2dict(filename):
    devices_dict = []
    with open(filename, "r") as f:
        csvreader = csv.reader(f)
        next(csvreader)   # ignore header
        for device_csv in csvreader:
            device_dict = {
                "device_id": device_csv[0].strip(),
                "name": device_csv[1].strip(),
                "type": device_csv[2].strip(),
                "hardware_model": device_csv[3].strip(),
                "hardware_sn": device_csv[4].strip(),
                "software_version": device_csv[5].strip(),
                "software_last_update": device_csv[6].strip(),
                "network_type": device_csv[7].strip(),
                "network_mac": device_csv[8].strip(),
                "network_ipv4": device_csv[9].strip(),
                "state": device_csv[10].strip()
            }
            devices_dict.append(device_dict)
    return devices_dict

@app.route("/v1/devices")
async def device(request):
    # 查看devices.csv是否存在
    if not os.path.exists(DATA_FILE_NAME):
        return json({"status": "Can't find {}".format(os.path.abspath(DATA_FILE_NAME))})
    
    # 获取设备信息
    devices = csv2dict(DATA_FILE_NAME)

    # 返回设备信息
    return json(devices)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000)
