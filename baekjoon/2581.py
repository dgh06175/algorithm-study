def is_prime(n):
    if n < 2:
        return False
    for v in range(2, n // 2 + 1):
        if n % v == 0:
            return False
    return True


m = int(input())
n = int(input())

sum = 0
min_num = 100000

for i in range(m, n + 1):
    if is_prime(i):
        sum += i
        min_num = min(i, min_num)

if min_num == 100000:
    print(-1)
else:
    print(sum)
    print(min_num)
