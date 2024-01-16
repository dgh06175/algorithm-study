def getDiff(arr, toggle, token, i, j):
    count = 0
    color = ["W", "B"]
    for x in range(i, i + 8):
        for y in range(j, j + 8):
            if i * j % 2 == 0:
                if color[toggle] != token:
                    count += 1
            else:
                if color[toggle] == token:
                    count += 1
                
            if toggle == 1:
                toggle = 0
            else:
                toggle = 1
    return count


def getMin(arr, i, j, n, m):
    if i + 8 >= n or j + 8 >= m:
        return n * m
    white_start = getDiff(arr, 0)
    black_start = getDiff(arr, 1)
    return min(white_start, black_start)


n, m = map(int, input().split())

arr = [0 for _ in range(n)]

for i in range(n):
    arr[i] = list(input())

for i in range(n):
    for j in range(m):
        pass

print(getDiff(arr, 1, 'W', 0, 0))
