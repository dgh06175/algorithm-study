from itertools import combinations
ary = list(map(int, input().split()))

k = ary[0]
ary.remove(k)
lotto = combinations(ary, 6)

for i in lotto:
  for j in i:
    print(j, end=' ')
  print()