# 使用 Ubuntu 22.04 作为基础镜像
FROM ubuntu:22.04

# 指定工作目录为 /app
WORKDIR /app
# 将主机文件系统中的 exp_pyms_api_demo 复制到镜像中
COPY exp_pyms_api_demo exp_pyms_api_demo

# 指示该服务使用的端口为 8000
EXPOSE 8000

# 指定容器的启动命令，device.csv 将存储在容器的 /var/lib/exp_pyms_api_demo/ 目录下，因此该目录应持久化
CMD ["/app/exp_pyms_api_demo", "/var/lib/exp_pyms_api_demo/device.csv"]