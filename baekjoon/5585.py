n = 1000 - int(input())
count = 0
ary = [500, 100, 50, 10, 5, 1]
for c in ary:
    if n >= c:
        count += n // c
        n -= n // c * c
print(count)
