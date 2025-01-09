n = int(input())

# dp[i] == i 를 1로 만드는 최소 연산 횟수
dp = [1000000] * (n * 10)

dp[1] = 0

for v in range(1, n + 1):
    dp[v * 3] = min(dp[v * 3], dp[v] + 1)
    dp[v + 1] = min(dp[v + 1], dp[v] + 1)
    dp[v * 2] = min(dp[v * 2], dp[v] + 1)

print(dp[n])

#
# dp = [1000000] * (n + 1)
#
#
# def foo(x):
#     if x == 1:
#         return 0
#     if dp[x] != 1000000:
#         return dp[x]
#     if x % 3 == 0:
#         dp[x] = min(dp[x], foo(x // 3))
#     if n % 2 == 0:
#         dp[x] = min(dp[x], foo(x // 2))
#     dp[x] += 1
#     return dp[x]
#
#
# print(foo(n))
