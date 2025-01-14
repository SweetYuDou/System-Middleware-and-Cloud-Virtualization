# transaction.py
class Transaction:
    def __init__(self):
        self._actions = []
        self._undo_actions = []
        self._cnt = 0  # 执行过计数

    def add_action(self, action, undo_action):
        """添加子操作和对应的撤销操作"""
        self._actions.append(action)
        self._undo_actions.append(undo_action)

    def commit(self):
        """提交所有操作"""
        for i in range(len(self._actions)):
            self._cnt += 1
            self._actions[i]()

    def rollback(self):
        """回滚已进行的操作"""
        for i, undo in enumerate(reversed(self._undo_actions)):
            if len(self._undo_actions) - i <= self._cnt:
                undo()
        self._cnt = 0