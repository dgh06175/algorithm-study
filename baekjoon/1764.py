n, m = map(int, input().split())

a = set()
b = set()

for _ in range(n):
    a.add(input())

for _ in range(m):
    b.add(input())

c = sorted(list(a.intersection(b)))

print(len(c))
for v in c:
    print(v)
