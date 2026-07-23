from collections import deque

n, m = 0, 0

def solution(maps):
    answer = 0
    global n
    global m
    n, m = len(maps), len(maps[0])
    distance = [[10000] * m for _ in range(n)]
    bfs(maps, distance, 0, 0)
    answer = distance[n-1][m-1]
    if answer == 10000:
        return -1
    return answer


def bfs(maps, distance, x, y):
    queue = deque([(x, y)])
    distance[x][y] = 1
    
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    while queue:
        x, y = queue.popleft()
        dist = distance[x][y]
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if validate(nx, ny) and maps[nx][ny] == 1 and distance[nx][ny] > dist + 1:
                queue.append((nx, ny))
                distance[nx][ny] = dist + 1
                
                
def validate(x, y):
    if x < 0 or y < 0 or x >= n or y >= m:
        return False
    return True
    