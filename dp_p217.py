n = int(input())

# d = [1000000] * 30001

# d[1] = 0

# for i in range(1, n + 1):
#   if d[i + 1] > d[i] + 1:
#     d[i + 1] = d[i] + 1
#   if d[i * 2] > d[i] + 1:
#     d[i * 2] = d[i] + 1
#   if d[i * 3] > d[i] + 1:
#     d[i * 3] = d[i] + 1
#   if d[i * 5] > d[i] + 1:
#     d[i * 5] = d[i] + 1

d = [0] * 30001

for i in range(2, n + 1):
  d[i] = d[i - 1] + 1
  if i % 5 == 0:
    d[i] = min(d[i], d[i//5] + 1)
  if i % 3 == 0:
    d[i] = min(d[i], d[i//3] + 1)
  if i % 2 == 0:
    d[i] = min(d[i], d[i//2] + 1)

print(d[n])