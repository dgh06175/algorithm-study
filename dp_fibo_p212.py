i = int(input())

d = [0] * 1000

# def fibo(n):
#   if n == 1 or n == 2:
#     return 1
#   if d[n] != 0:
#     return d[n]
#   else:
#     d[n] = fibo(n-1) + fibo(n-2)
#   return d[n]

# print(fibo(i))

d[1], d[2] = 1, 1

for i in range(3, i+1):
  d[i] = d[i-1] + d[i-2]

print(d[i])