def getMin(arr, i, j, n, m):
    if i + 8 >= n or j + 8 >= m:
        return n * m
    count = 0
    color = ["W", "B"]
    toggle = 0
    for x in range(i, i + 8):
        for y in range(j, j + 8):
            if arr[x][y] != color[0]:
                pass


n, m = map(int, input().split())

arr = [0 for _ in range(n)]

for i in range(n):
    arr[i] = list(input().split())

for i in range(n):
    for j in range(m):
        pass
