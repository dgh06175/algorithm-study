n, m = map(int, input().split())
parent = [x for x in range(n+1)]


def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(a1, b1):
    a1 = find(a1)
    b1 = find(b1)
    if a1 < b1:
        parent[b1] = a1
    else:
        parent[a1] = b1


for _ in range(m):
    sign, a, b = map(int, input().split())
    if sign == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")
