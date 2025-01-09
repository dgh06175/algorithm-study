n = int(input())
ary = list(map(int, input().split()))

dp = [1] * n
for i in range(1, len(ary)):
    for j in range(i):
        if ary[i] > ary[j]:
            dp[i] = max(dp[i], dp[j] + 1)
print(max(dp))
