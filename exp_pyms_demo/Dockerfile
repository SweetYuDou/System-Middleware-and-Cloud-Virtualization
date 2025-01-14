# 基于Python最新基础镜像
FROM python:3.10-slim-buster
LABEL maintainer="<1464935327@qq.com>"

# 工作目录
WORKDIR /app

# 安装Python库
COPY requirements.txt requirements.txt
RUN apt-get update && apt-get install -y \
    && pip3 install -r requirements.txt

# 清理apt软件包缓存
RUN rm -rf /var/lib/apt/lists/*

# 复制当前文件夹中全部文件到镜像中
COPY . .

# 暴露服务端口8000
EXPOSE 8000

# 容器启动时执行命令
CMD [ "python3", "server.py" ]