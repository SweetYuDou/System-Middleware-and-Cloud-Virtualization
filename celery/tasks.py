from celery import Celery
import json
import requests
from transaction import Transaction
# 自行替换为Redis的内网访问地址
REDIS_HOST = 'r-bp1j6i81oi3257cnvt.redis.rds.aliyuncs.com'
REDIS_USERNAME = 'yjs'
REDIS_PASSWORD = 'UESTCyjs123'
REDIS_PORT = 6379

celery = Celery(
    "tasks",
    broker=f"redis://{REDIS_USERNAME}:{REDIS_PASSWORD}@{REDIS_HOST}:{REDIS_PORT}/0",
    backend=f"redis://{REDIS_USERNAME}:{REDIS_PASSWORD}@{REDIS_HOST}:{REDIS_PORT}/1"
)

def add_users(url, user_list, result):
    """创建用户"""
    print("add_users()")
    resp = requests.post(url, json={"entities": user_list}, timeout=20)
    s = f"{resp.status_code}\n{resp.content.decode()}"
    content = json.loads(resp.content)
    print(s)
    if resp.status_code!= 200 or content["status"]!= 1:
        raise Exception(s)
    result.extend(content["data"])

def undo_add_users(url, ids):
    """删除用户"""
    print("undo_add_users()")
    resp = requests.delete(url, json={"ids": ids}, timeout=10)
    s = f"{resp.status_code}\n{resp.content.decode()}"
    content = json.loads(resp.content)
    print(s)
    if resp.status_code!= 200 or content["status"]!= 1:
        print("回滚异常")

def send_mail(url, recipient, subject, body):
    """发送邮件"""
    print("send_mail()")
    data = {"recipient": recipient, "subject": subject, "body": body}
    resp = requests.post(url, json={"action": "send_email", "data": data}, timeout=20)
    s = f"{resp.status_code}\n{resp.content.decode()}"
    content = json.loads(resp.content)
    print(s)
    if resp.status_code!= 200:
        raise Exception(s)

def undo_send_mail():
    """撤销邮件"""
    print("undo_send_mail()")
    # 撤销邮件逻辑，需自行实现，此处仅做演示
    pass

@celery.task(name='tasks.create_and_notify_users')
def create_and_notify_users(user_url, user_list, msg_url, recipient, subject, body):
    user_ids = []

    transaction = Transaction()

    transaction.add_action(
        lambda: add_users(f"{user_url}/users", user_list, user_ids),
        lambda: undo_add_users(f"{user_url}/users", user_ids)
    )

    transaction.add_action(
        lambda: send_mail(f"{msg_url}/invoke", recipient, subject, body),
        lambda: undo_send_mail()
    )

    try:
        transaction.commit()
        return user_ids
    except Exception as e:
        transaction.rollback()
        raise e