import sys

n, m = map(int, input().split())

s = {}

for _ in range(n):
    a, b = sys.stdin.readline().strip().split()
    s[a] = b

for _ in range(m):
    findUrl = sys.stdin.readline().strip()
    print(s[findUrl])

print(s)
