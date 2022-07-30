n = int(input())

def isOk(x, y):
  if x <= n-1 and x >= 0 and y >= 0 and y <= n-1:
    return True
  else:
    return False

# 좌측아래 방향으로 시작
#
# 방향이 좌측 아래일떄
# 좌측 아래칸이 범위 안일 경우, 좌측 아래로 이동
# 좌측 아래칸이 범위 밖일경우, 아래로 이동 후 방향 전환
# 하나 아래도 범위 밖일 경우, 우측으로 이동 후 방향 전환
# 우측도 범위 밖일경우, 끝
#
# 우측 위로 갈때
# 우측 위에칸이 범위 안일 경우, 우측 위로 이동
# 우측 위에칸이 범위 밖일경우, 우측으로 이동
# 우측도 범위 밖일경우, 아래로 이동
# 아래도 범위 밖일경우, 끝

ary = [[0] * n for _ in range(n)]

count = 1
x, y = 0, 0
isDirectionLeftDown = True

while(True):
  ary[x][y] = count
  count+=1
  if isDirectionLeftDown: # 방향이 좌측아래 일때
    if isOk(x+1, y-1): # 좌측아래로 이동
      x, y = x+1, y-1
    elif isOk(x+1, y): # 아래로 이동
      x, y = x+1, y
      isDirectionLeftDown = False
    elif isOk(x, y+1): # 우측으로 이동
      x, y = x, y+1
      isDirectionLeftDown = False
    else:
      break
  else: # 방향이 우측위 일때
    if isOk(x-1, y+1):
      x, y = x-1, y+1
    elif isOk(x, y+1):
      x, y = x, y+1
      isDirectionLeftDown = True
    elif isOk(x+1, y):
      x, y = x+1, y
      isDirectionLeftDown = True
    else:
      break

for i in range(n):
  for j in range(n):
    print(ary[i][j], end=' ')
  print()