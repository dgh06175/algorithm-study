from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 모든 좌표의 크기를 2배로 늘려서 풀었다. 그래야 테두리 분별이 쉽다.
    MAX_LEN = 102
    map = [[0] * MAX_LEN for _ in range(MAX_LEN)]
    characterX *= 2
    characterY *= 2
    itemX *= 2
    itemY *= 2
    
    # 사각형 내부인 부분 모두 1로 표시
    for rect in rectangle:
        x1, y1, x2, y2 = rect
        for x in range(x1*2, x2*2 + 1):
            for y in range(y1*2, y2*2 + 1):
                map[x][y] = 1
                
    # 테두리 모두 2로 표시
    dx = [1, 1, 1, 0, 0, -1, -1, -1]
    dy = [1, 0, -1, 1, -1, 1, 0, -1]
    for i in range(MAX_LEN):
        for j in range(MAX_LEN):  
            if map[i][j] == 1:
                for di in range(8):
                    nx = i + dx[di]
                    ny = j + dy[di]
                    if map[nx][ny] == 0:
                        map[i][j] = 2
                        break
    # bfs
    distance = [[10000] * MAX_LEN for _ in range(MAX_LEN)]
    queue = deque([(characterX, characterY)])
    distance[characterX][characterY] = 0
    
    dx = [1,0,0,-1]
    dy = [0,1,-1,0]
    while queue:
        x, y = queue.popleft()
        for d_i in range(len(dx)):
            nx = x + dx[d_i]
            ny = y + dy[d_i]
            if map[nx][ny] == 2 and distance[nx][ny] > distance[x][y] + 1:
                queue.append((nx, ny))
                distance[nx][ny] = distance[x][y] + 1
    
    # 2배로 늘렸었으니까 2로 나눈값이 정답이다.
    answer = distance[itemX][itemY]
    return answer / 2
