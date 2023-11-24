scoreArr = {
    "A+": 4.5,
    "A0": 4.0,
    "B+": 3.5,
    "B0": 3.0,
    "C+": 2.5,
    "C0": 2.0,
    "D+": 1.5,
    "D0": 1.0,
    "F": 0.0,
    "P": 0.0
}

arr = []
학점_count = 0
score_sum = 0

for _ in range(20):
    arr.append(input().split())

for v in arr:
    if v[2] == "P":
        continue
    학점_count += float(v[1])
    score_sum += float(v[1]) * scoreArr[v[2]]


print(score_sum / 학점_count)
