def left_down(x, y):
  return x+1, y-1

def right_down(x, y):
  return x+1, y+1

def right_up(x, y):
  return x-1, y+1

def left_up(x, y):
  return x-1, y-1

def put_str(x, y, eng_num):
  eng_num += 1
  if eng_num > 90:
    eng_num = 65
  ary[x][y] = chr(eng_num)
  return eng_num
  

n = int(input())

# 2*n-1 크기의 배열 생설
ary = [[" "] * (2*n-1) for _ in range(2*n-1)]
# chr(65) is "A", chr(90) is "Z"
x = 0
y = n - 1
eng_num = 65

ary[x][y] = chr(65)
for i in range(n, 1, -1): # n..3 2
  for j in range(i - 1):
    x, y = left_down(x, y) # 다음칸으로 이동
    eng_num = put_str(x, y, eng_num) # 해당 칸에 문자 입력

  for j in range(i - 1):
    x, y = right_down(x, y) # 다음칸으로 이동
    eng_num = put_str(x, y, eng_num) # 해당 칸에 문자 입력

  for j in range(i - 1):
    x, y = right_up(x, y) # 다음칸으로 이동
    eng_num = put_str(x, y, eng_num) # 해당 칸에 문자 입력

  for j in range(i - 2):
    x, y = left_up(x, y) # 다음칸으로 이동
    eng_num = put_str(x, y, eng_num) # 해당 칸에 문자 입력
    
  x, y = x, y-1
  eng_num = put_str(x, y, eng_num)


for i in range(2 * n - 1):
  for j in range(2 * n - 1):
    print(ary[i][j], end=' ')
  print("")
