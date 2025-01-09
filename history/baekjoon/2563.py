count = int(input())

arr = [[0 for _ in range(100)] for _ in range(100)]

for _ in range(count):
    left, right = map(int, input().split())
    for i in range(left, left + 10):
        for j in range(right, right + 10):
            arr[i][j] = 1
        
answer = 0

for v in arr:
    answer += sum(v)

print(answer)