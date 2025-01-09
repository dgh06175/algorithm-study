n = int(input())

s = set()

for _ in range(n):
    a, b = input().split()
    if b == "enter":
        s.add(a)
    elif b == "leave":
        if a in s:
            s.remove(a)

a = list(s)

for v in sorted(a, reverse=True):
    print(v)
