n, k = map(int, input().split())

W = [0] * 101
V = [0] * 101

dp = [[0] * 101 for _ in range(100001)]

# dp[i][w] = i 까지의 아이템을 넣을 수 있는데, 가방의 용량이 w 일때 최대 무게값
# dp[i][w] = i번의 물건을 넣냐 마냐의 문제이다.
# = 남은 공간 (dp[i-1][w-W[i]) 에 현재 무게 넣기 (+V[i])

for i in range(1, n + 1):
    W[i], V[i] = map(int, input().split())

for i in range(1, n + 1):
    for w in range(0, W[i]):
        dp[i][w] = dp[i - 1][w]
    for w in range(W[i], k + 1):
        if w - W[i] >= 0:
            dp[i][w] = max(dp[i - 1][w - W[i]] + V[i], dp[i - 1][w])

max_num = 0
for d in dp:
    tmp = max(d)
    max_num = max(tmp, max_num)

print(max_num)
