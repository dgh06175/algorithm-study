import math

mini, maxi = map(int, input().split())

# TODO: 나중에 풀기

# 소수: 1과 본인을 제외한 수로 나누어 떨어지지 않는다.
# 1보타 큰 제곱수로 나누어 떨어지지 않는 수.

isPrime = [True] * (maxi + 1)

isPrime[0] = False
isPrime[1] = False

for i in range(2, maxi):  # 제곱수가 아니면 건너뛰기?
    if not int(math.sqrt(i)) * int(math.sqrt(i)) == i:
        continue

    if not isPrime[i]:
        continue
    for j in range(i * i, maxi, i):
        isPrime[j] = False

ans = 0
for m in range(mini, maxi + 1):
    if isPrime[m]:
        ans += 1

print(ans)
