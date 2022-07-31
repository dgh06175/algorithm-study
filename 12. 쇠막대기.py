#  ()(((()())(())()))(()) 주어진 값
#  1 (((1 1 )(1 )1 ))(1 ) # 레이저들을 1로 치환한다
# 막대기의 개수 = '('또는')'의 개수 = 5
# = (          )( )
# +  (        )
# +   (  )()

#최대 층의 개수 = n/2

data = list(input())
n = len(data)

# 레이저 빼내기
lazor = [0] * n
for i in range(n-1):
  if data[i] == '(' and data[i+1] == ')':
    data[i] = ' '
    data[i+1] = ' '
    lazor[i] = 1

# ()(((()())(())()))(()) data
#   (((    )(  )  ))(  ) data
# 1000010100010010000100 lazor
answer = data.count('(')

floors = []

while '(' in data:
  start = 0
  last = 0
  # 처음 '('이 나오면 층의 시작, 가면서 '(' 개수 카운트
  # ')'이 나올땐 카운트한'(' 개수 감소, 0될때 나온')'가 층의 끝
  count = 0  
  for i in range(n):
    if data[i] == '(':
      if count == 0:
        start = i
        data[i] = ' '
      count += 1
    if data[i] == ')':
      count -= 1
      if count == 0:
        last = i
        data[i] = ' '
        break
  
  floor = [0] * n
  isFlr = False
  for i in range(n):
    if i == start:
      isFlr = True
    if isFlr:
      floor[i] = 1
    if i == last:
      break
  floors.append(floor)

for i in range(n):
  if lazor[i] == 1:
    for flr in floors:
      if flr[i] == 1:
        answer += 1

# for i in data:
#   print(i, end='')
# print()
# for i in floors:
#   for j in i:
#     print(j, end='')
#   print()

print(answer)