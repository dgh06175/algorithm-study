answer = 0

n = int(input())

for a in range(1, 501):
    for b in range(1, 501):
        if a * a == b * b + n:
            answer += 1

print(answer)
