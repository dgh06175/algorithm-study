n = int(input())
food = list(map(int, input().split()))

d = [0] * 100
d[0] = food[0]
d[1] = max(food[0], food[1])

for i in range(2, n): # 2 3
  d[i] = max(d[i-1], food[i] + d[i-2])

print(d[n - 1])