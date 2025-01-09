a0, a1 = map(int, input().split())

c = int(input())
n0 = int(input())

if abs(a1) * n0 + a0 <= c * n0 and a1 <= c:
    print(1)
else:
    print(0)