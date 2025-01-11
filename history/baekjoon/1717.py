import sys

sys.setrecursionlimit(10 ** 6)

parent = [i for i in range(1000001)]


def my_find(x):
    if parent[x] != x:
        parent[x] = my_find(parent[x])
    return parent[x]


def my_union(a1, b1):
    A = my_find(a1)
    B = my_find(b1)
    parent[A] = B


n, m = map(int, sys.stdin.readline().split())
for _ in range(m):
    sign, a, b = map(int, sys.stdin.readline().split())
    if sign == 0:
        my_union(a, b)
    else:
        if my_find(a) == my_find(b):
            print("YES")
        else:
            print("NO")
