from collections import deque
# 애벌레는 사과껍질을 먹으면 몸의 길이가 늘어난다.
# 벽이나 자신의 몸에 부딪히면 종료
# n*n
# 매 초마다 머리를 다음칸에 위치
# 껍질이 있다면 몸 늘어나서 머리위치까지 그대로 몸이됨
# 껍질이 없다면 꼬리가 한칸 당겨져옴
# 몇 초에 부딪혀서 게임 종료?
# 시작점:0초 가장자리: 범위밖
# 0010000000
# 0000100000
# 0001000000
# 0000100000
# 0000010000
# 0000000000
# 0000000000
# 0000000000
# 0000000000
# 0000000000

# 1: 사과껍질 2: 자기몸
n = int(input())
m = int(input())
# 좌표는 1부터 n까지 유효범위
world = [[0] * (n+1) for _ in range(n+1)]
for _ in range(m):
  a, b = map(int, input().split())
  world[a][b] = 1
d = int(input())
move = deque()
for _ in range(d):
  a, b = map(str, input().split())
  move.append((int(a), b))

###### 입력받는 부분 끝 ######

dx = [0, 1, 0, -1]
dy = [1, 0, -1 ,0]
# 0:우측 1:아래 2:왼쪽 3:위쪽
# 위치, 현재 방향, 돌 방향을 받고 다음 위치를 반환하는 함수
def turn(direction, where):
  if where == 'R': #우측으로 돈다면
    if direction == 3:
      direction = 0
    else:
      direction += 1

  elif where == 'L': # 왼쪽으로 돈다면
    if direction == 0:
      direction = 3
    else:
      direction -= 1

  return direction

x, y = 1, 1 #초기좌표
t = 0 #시간
body = deque()
body.append((x,y)) #몸 초기위치
world[x][y] = 2
direct = 0 #방향 초기위치(오른쪽))

######## 초기 설정부분 끝##########

# 매 초마다 머리를 다음칸에 위치
# 껍질이 있다면 몸 늘어나서 머리위치까지 그대로 몸이됨
# 껍질이 없다면 꼬리가 한칸 당겨져옴
# 몇 초에 부딪혀서 게임 종료?
# 시작점:0초 가장자리: 범위밖

# 좌표는 1부터 n까지 유효범위
# 1: 사과껍질 2: 자기몸

while t <= 10000:
  t+=1
  wh = 'N'
  x += dx[direct]
  y += dy[direct]
  if move:
    if move[0][0] == t: #방향 전환할 차례이면
      # 좌표를 다음 위치로 바꿔줌
      wh = move[0][1]
      move.popleft()
  direct = turn(direct, wh)

  # 벽이나 자기 몸에 닿았다면
  #print(x, y)
  if x<1 or x>n or y<1 or y>n:
    break
  if world[x][y] == 2:
    break

  if world[x][y] == 1: # 사과라면 그냥 추가
    world[x][y] = 2
    body.append((x,y))
  elif world[x][y] == 0: # 빈칸이라면 꼬리 자르기
    world[x][y] = 2
    body.append((x,y))
    rm = body.popleft()
    world[rm[0]][rm[1]] = 0

print(t)