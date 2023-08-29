# 1 7 8 10 13
# 1 4 7 10 13

# 등차수열은 대칭의 합이 항상 같다.

# 500 * 500 = 250000 = O(N^2)로 풀어야한다.
# 공차를 구하고 몇개의 카드를 바꿔야하는지 검사하는건 O(N)
# 공차: 가장 작은 값과 큰값부터 내려가면서 차이 / 카드 간격의 차이

# -1 1 3 3 6
# 2, 4, 4, 7

n = int(input())
ary = list(map(int, input().split()))

m = min(ary)

ans = n - 1
for i in range(1, n):
  cnt = 0
  if (ary[i] - m) % i == 0:
    d = (ary[i] - m) // i
    for j in range(1, n):
      if not m + d * j == ary[j]:
        cnt += 1
  else:
    cnt = n-1
    
  if ans > cnt:
    ans = cnt
  

print(ans)