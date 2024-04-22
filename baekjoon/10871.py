n, x = map(int, input().split())

ary = list(map(int, input().split()))

for v in ary:
    if v < x:
        print(v, end=" ")
