n = int(input())

arr = [[0] * (n+1) for _ in range(n+1)]



cnt = 1
x = 0
y = 0

dx = 1
dy = -1
downLeft = True
k = 0
for l in range(1, n+1):
    k += l

for i in range(k):
    arr[x][y] = cnt
    cnt += 1
    if downLeft:
        nx = x + 1
        ny = y - 1
    if not downLeft:
        nx = x - 1
        ny = y + 1
    change = False
    if nx < 0:
        nx = 0
        change = True
    if nx > n-1:
        nx = n-1
        change = True
    if ny < 0:
        ny = 0
        change = True
    if ny > n-1:
        ny = n-1
        change = True

    if change:
        if downLeft:
            downLeft = False
        else:
            downLeft = True
    x = nx
    y = ny


if n % 2 == 1:
    y += 1
    dx = 1
    dy = -1
    downLeft = False
else:
    x += 1
    dx = -1
    dy = 1
    downLeft = True

k = n * n - int(((n+1)/2) * n)
for i in range(k):
    arr[x][y] = cnt
    cnt += 1
    if downLeft:
        nx = x + 1
        ny = y - 1
    if not downLeft:
        nx = x - 1
        ny = y + 1
    change = False
    if ny > n-1:
        ny = n - 1
        nx = nx + 2
        change = True

    if nx > n-1:
        ny = ny + 2
        nx = n - 1
        change = True

    if nx < 0:
        nx = 0
        change = True
    if ny < 0:
        ny = 0
        change = True

    if change:
        if downLeft:
            downLeft = False
        else:
            downLeft = True
    x = nx
    y = ny

max_len = len(str(n * n))


for i in range(n):
    for j in range(n):
        if j == 0:
            int_len = len(str(arr[i][j]))
            a = max_len - int_len
            print(" " * (a - 1), end='')
            print(arr[i][j], end=' ')
        elif j == n-1:
            int_len = len(str(arr[i][j]))
            a = max_len - int_len
            print(" " * a, end='')
            print(arr[i][j], end='')
        else:
            int_len = len(str(arr[i][j]))
            a = max_len - int_len
            print(" " * a, end='')
            print(arr[i][j], end=' ')
    print()




