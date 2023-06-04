from collections import deque
'''

3 3
D.*
...
.S.

'''

r, c = map(int, input().split())
worldMap = [[]for _ in range(r)]
visited = [[False] * c for _ in range(r)]
for i in range(r):
    worldMap[i] = input()

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

for v in worldMap:
    print(v)


def bfs(start_x, start_y, plank, visited):
    queue_x = deque([start_x])
    queue_y = deque([start_y])
    while queue_x and queue_y:
        x = queue_x.popleft()
        y = queue_y.popleft()
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx >= 1 and nx <= n and ny >= 1 and ny <= m:
                if not visited[nx][ny]:
                    queue_x.append(nx)
                    queue_y.append(ny)
                    plank[nx][ny] = plank[x][y] + 1
                    visited[nx][ny] = True
