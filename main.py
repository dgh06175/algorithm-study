from collections import deque

q = deque()
q.append(1)
print(q)
q.extend([3,4])
print(q)
q.reverse()
print(q)
q.rotate(1)
print(q)