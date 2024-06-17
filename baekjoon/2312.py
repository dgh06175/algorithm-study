import math

t = int(input())

for _ in range(t):
    n = int(input())
    if n == 1:
        print("1 1")
        continue
    for i in range(2, int(math.sqrt(n) + 1)):
        count = 0
        while n % i == 0:
            n //= i
            count += 1
        if count != 0:
            print(f"{i} {count}")
    if n > 1:
        print(f"{n} 1")
