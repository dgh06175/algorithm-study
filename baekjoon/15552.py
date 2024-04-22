import sys

t = int(input())

for _ in range(t):
    n, m = map(int, sys.stdin.readline().rstrip().split())
    print(n + m)
