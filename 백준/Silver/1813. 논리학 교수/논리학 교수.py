n = int(input())

ary = list(map(int, input().split()))

answer = -1

for i in range(n + 1):
    count = 0
    for a in ary:
        if a == i:
            count += 1
    if count == i:
        answer = i

print(answer)
