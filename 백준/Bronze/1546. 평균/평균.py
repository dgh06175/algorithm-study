n = int(input())
scoreArr = list(map(int, input().split()))
print(sum(scoreArr) / n / max(scoreArr) * 100)