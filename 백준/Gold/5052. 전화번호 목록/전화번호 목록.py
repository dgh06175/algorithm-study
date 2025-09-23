import sys

input = sys.stdin.readline


def foo(n):
    numbers = sorted([input().strip() for _ in range(n)])
    for i in range(n - 1):
        if numbers[i] == numbers[i + 1][:len(numbers[i])]:
            return "NO"
    return "YES"


t = int(input().strip())
for _ in range(t):
    n = int(input().strip())
    print(foo(n))
