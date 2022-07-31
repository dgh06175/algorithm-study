# n = 1 ~ 80000
# 소의 키의 나열이 있다.
# 어떤 소는 오른쪽으로 자신보다 크거나 같은 크기의 소가 나오기 전까지의 자신보다 작은 소만 볼 수있다.
# 다이나믹프로그래밍 문제이다.

# sumary[i]는 sumary[i+1] + 내가 보이는 녀석들

n = int(input())
ary = []
for _ in range(n):
  ary.append(int(input()))

def dp(index, sumary, ary, n):
  if index < 0:
    return
  x = 0
  for i in range(index+1, n):
    if ary[index] < ary[i]:
      break
    x+=1
  sumary[index] = x + sumary[index + 1]
  dp(index-1, sumary, ary, n)

  
#    ary 5 2 4 2 6 1
#  check 6 4 6 6 0 0
# sumary 5 2 2 1 1 0
sumary = [0] * n
sumary[n-1] = 0
dp(n-2, sumary, ary, n)
print(sumary[0])



    