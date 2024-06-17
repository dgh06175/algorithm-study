import math

# def is_prime(n):
#     if n < 2:
#         return False
#     for i in range(2, int(math.sqrt(n)) + 1):
#         if n % i == 0:
#             return False
#     return True
#
#
# n = int(input())
# ary = list(map(int, input().split()))
# answer = 0
# for v in ary:
#     if is_prime(v):
#         answer += 1
#
# print(answer)
import math

_ = int(input())
ary = list(map(int, input().split()))
isPrime = [True] * 1001

isPrime[0] = False
isPrime[1] = False

for i in range(2, int(math.sqrt(1000) + 1)):
    if not isPrime[i]:
        continue
    for j in range(i * i, 1001, i):
        isPrime[j] = False

answer = 0
for v in ary:
    if isPrime[v]:
        answer += 1

print(answer)
