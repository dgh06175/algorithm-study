n, m = map(int, input().split())
# 맵 크기가 n * m
x, y, direction = map(int, input().split())
# x = 세로, y = 가로 현재위치
# d = 0:북 1:동 2:남 3:서

# 방문했던 맵 기록할 맵 리스트 선언
visited_world = [[0] * m for _ in range(n)]

# 맵 2차원으로 한줄씩 입력받기
world = []
for i in range(n):
  world.append(list(map(int, input().split())))

# direction = 0:북 1:동 2:남 3:서
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
  
# 회전하는 함수
def turn_left():
  global direction
  direction -= 1
  if direction == -1:
    direction = 3


turn_count = 0
result = 1
visited_world[x][y] = 1
while True:
  turn_left()
  nx = x + dx[direction]
  ny = y + dy[direction]
  if world[nx][ny] == 0 and visited_world[nx][ny] == 0: # 안가본 육지이면 앞으로 한칸 이동
    x = nx
    y = ny
    turn_count = 0
    result += 1
    visited_world[nx][ny] = 1
    print(x, y, "로 이동")
    continue
  else:
    turn_count += 1

  if turn_count == 4:
    nx = x - dx[direction]
    ny = y - dy[direction]
    if world[nx][ny] == 0: # 뒤가 방문했더라도 갈수있으면 이동
      x = nx
      y = nx
      turn_count = 0
      visited_world[nx][ny] = 1
      print(x, y, "로 이동")
    else:
      break
      

print(result)
