n = int(input())
subject = list(map(int, input().split()))
b, c = map(int, input().split())
answer = len(subject)
for sub in subject:
    if sub > b:
        remain_person = sub - b
        answer += remain_person // c
        if remain_person % c != 0:
            answer += 1

print(answer)