from collections import deque
import queue

q = queue.Queue()
n = int(input())

queue = deque([i for i in range(1, n + 1)])

while len(queue) > 1:
    x = queue.popleft()
    print(x, end=' ')
    queue.append(queue.popleft())

if queue:
    print(queue.pop())
