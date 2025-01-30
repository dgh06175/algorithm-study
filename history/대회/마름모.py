n = int(input())

arr = [[' '] * (2*n + 1) for _ in range(2*n + 1)]

alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
count = 0

def appendAlpha(cnt, arr, x, y):
    if cnt == len(alphabet):
        cnt = 0
    arr[x][y] = alphabet[cnt]
    return cnt + 1



x = 0
y = n-1
dx = 0
dy = 0
for m in range(n, 0, -1):
    dx, dy = 1, -1
    for _ in range(m-1):
        count = appendAlpha(count, arr, x, y)
        x += dx
        y += dy
    dx, dy = 1, 1
    for _ in range(m-1):
        count = appendAlpha(count, arr, x, y)
        x += dx
        y += dy
    dx, dy = -1, 1
    for _ in range(m-1):
        count = appendAlpha(count, arr, x, y)
        x += dx
        y += dy
    dx, dy = -1, -1
    for _ in range(m-2):
        count = appendAlpha(count, arr, x, y)
        x += dx
        y += dy
    count = appendAlpha(count, arr, x, y)
    y -= 1


isBlank = True

for i in range(2 * n - 1):
    isBlank = True
    for j in range(2 * n - 1):
        if not isBlank and arr[i][j] == ' ':
            isBlank = True
            break
        if arr[i][j] == ' ':
            isBlank = True
        else:
            isBlank = False

        if not isBlank and arr[i][j+1] == ' ':
            print(arr[i][j], end='')
        else:
            print(arr[i][j], end=' ')
    print()

