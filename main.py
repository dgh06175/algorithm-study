n, m = map(int, input().split())
moneys = []

for _ in range(n):
  moneys.append(int(input()))

d = [20000] * (m + 1)
d[0] = 0

for i in range(m+1):
  for money in moneys:
    # print("i = ", i, "money = ", money)
    if i-money >= 0:
      d[i] = min(d[i-money]+1, d[i])

if d[m] > 10001:
  print(-1)
else:
  print(d[m])