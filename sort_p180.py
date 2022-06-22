n = int(input())
ary = []
for i in range(n):
  input_data = input().split()
  ary.append((input_data[0], int(input_data[1])))

result = sorted(ary, key = lambda x: x[1])

for v in result:
  print(v[0], end=' ')