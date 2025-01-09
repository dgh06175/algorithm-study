from collections import deque

T = int(input())

dx = [-2, -1, 1, 2, 2, 1, -1, -2]
dy = [1, 2, 2, 1, -1, -2, -2, -1]

for _ in range(T):
    l = int(input())
    sx, sy = map(int, input().split())
    ex, ey = map(int, input().split())

    visited = [[False] * l for _ in range(l)]
    ary = [[0] * l for _ in range(l)]
    visited[sx][sy] = True

    q = deque([(sx, sy, 0)])
    while q:
        x, y, count = q.popleft()
        if x == ex and y == ey:
            print(count)
            break
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx >= 0 and nx < l and ny >= 0 and ny < l and not visited[nx][ny]:
                q.append((nx, ny, count + 1))
                visited[nx][ny] = True
