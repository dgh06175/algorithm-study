n = int(input())
ary = []
for _ in range(n):
    a, b = map(int, input().split())
    ary.append((a, b))

ary.sort(key=lambda x: x[1])

answer = 0
time = 0
for v in ary:
    if time <= v[0]:
        answer += 1
        time = v[1]

print(answer)
