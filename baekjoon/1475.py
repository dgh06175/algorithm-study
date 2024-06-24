import math

nary = list(map(int, input()))
ary = [0] * 10

for v in nary:
    if v == 9:
        ary[6] += 1
    else:
        ary[v] += 1

max_count = max(ary)

ary[6] = math.ceil(ary[6] / 2)

print(max(ary))
