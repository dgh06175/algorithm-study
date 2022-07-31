# N = 2, 4, 8, 16, 32, 64, 128 중 1

n = int(input())
ary = []
for i in range(n):
  ary.append(list(map(int, input().split())))

# 구간의 값을 모두 더한 값이 (구간의 길이^2)면 파랑, 0이면 흰색이다.

def myfunc(ary, length, x, y, wb):
  if ary[x][y] == -1: # 이미 계산한 부분이라면
    return
    
  sum = 0
  for i in range(length):
    for j in range(length):
      sum += ary[x+i][y+j]
      
  if sum == 0: # 합이 0 = 다 흰색
    wb[0]+=1
    for i in range(length):
      for j in range(length):
        ary[x+i][y+j] = -1
    return
    
  elif sum == length * length: # 합이 1 = 다 파란색
    wb[1]+=1
    for i in range(length):
      for j in range(length):
        ary[x+i][y+j] = -1
    return
    
  else:
    myfunc(ary, length//2, x, y, wb)
    myfunc(ary, length//2, x+length//2, y, wb)
    myfunc(ary, length//2, x, y+length//2, wb)
    myfunc(ary, length//2, x+length//2, y+length//2, wb)


wb = [0, 0]
myfunc(ary, n, 0, 0, wb)
print(wb[0])
print(wb[1])

