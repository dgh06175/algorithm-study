from collections import deque

n, m = map(int, input().split())
r, c, s, k = map(int, input().split())
map_ = [[0] * (m+1) for _ in range(n+1)]

dx = [2, 1, -1, -2, -2, -1, 1, 2]
dy = [1, 2, 2, 1, -1, -2, -2, -1]

visited = [[0] * (m+1) for _ in range(n+1)]
visited[r][c] = True
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

bfs(r, c, map_, visited)
print(map_[s][k])
