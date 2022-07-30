# from itertools import combinations
# # 위에서 몇가지를 뽑는 모든 경우의수를 해보고,
# # 각 경우마다 바로 아래의 숫자들의 집합도 만들어봐서
# # 위에서 뽑은 숫자들의 집합과 같을때의 집합의 최대 크기를 저장.

# n = int(input()) # 1~100
# ary1 = []
# ary2 = []
# for i in range(n):
#   ary1.append(i + 1)
#   ary2.append(int(input()))

# max = 0
# max_ary = []
# # i = 조합 원소 개수
# for i in range(n, 0, -1): # i = 1 ~ n
#   if max >= i:
#     continue
#   com_list = list(combinations(ary1, i))
#   # j = 완성된 조합의 번호
#   for j in range(len(com_list)):
#     temp_ary = []
#     for k in range(i):
#       temp_ary.append(ary2[com_list[j][k]-1])
#     temp_ary.sort()
#     # temp = tuple(temp_ary)
#     #print(f"{com_list[j]}vs{temp}", end='')
#     if temp_ary == list(com_list[j]):
#       if max < len(temp_ary):
#         max = len(temp_ary)
#         max_ary = temp_ary
#         continue
#   if max >= i:
#       continue
#         #print("MAX!",end='')
#       #print("!!",end='')
#     #print()

# print(max)
# for m in range(max):
#   print(max_ary[m])

# 사이클 문제이다. 재귀함수로 풀이


n = int(input()) # 1~100
ary1 = [0]
ary2 = [0]
for i in range(n):
  ary1.append(i + 1)
  ary2.append(int(input()))

#print(ary1, ary2)


def myFunc(start, visit, next):
  if start == next:
    return start
  if visit[next] == True:
    return 0
  visit[next] = True
  return myFunc(start, visit, ary2[next])
  

ary = []
for i in range(1, n+1):
  visit = [False] * (n+1)
  ary.append(myFunc(i, visit, ary2[i]))

#print(ary)
cnt = 0
for j in range(len(ary)):
  if ary[j] != 0:
    cnt+=1
    
print(cnt)
for k in range(len(ary)):
  if ary[k] != 0:
    print(ary[k])

