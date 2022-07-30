# 완제품의 조합법을 찾고
# 그 조합법의 조합법이 있다면 조합법의 조합법을 찾고,
# 조합법의 조합법이 없다면 정답에 더한다.

n = int(input()) # 3~100
# 1 ~ n-1 은 기본 또는 중간부품 번호 n은 완제품 번호
m = int(input()) # 3~100
ary = []
for _ in range(m):
  ary.append(list(map(int, input().split())))

combi = []
for i in range(len(ary)):
  if ary[i][0] == n:
    combi.append([ary[i][1], ary[i][2]])

# combi = 완제품의 조합법
while(True):
  tmp = []
  MoreCombi = False
  for x in range(len(combi)):
    cnt = False
    for y in range(len(ary)):
      if combi[x][0] == ary[y][0]: # 조합법의 조합법이 있으면
        tmp.append([ary[y][1], ary[y][2] * combi[x][1]])
        cnt = True
        MoreCombi = True
    if not cnt:
      tmp.append(combi[x])
  combi = tmp
  if not MoreCombi:
    break
      
answer = [0] * n
for i in range(len(combi)):
  answer[combi[i][0]] += combi[i][1]

for i in range(len(answer)):
  if answer[i] != 0:
    print(i, answer[i])