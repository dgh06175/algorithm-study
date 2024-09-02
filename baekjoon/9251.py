str1 = list(input())
str2 = list(input())

N = len(str1)
M = len(str2)

ary = [[0] * (M + 1) for _ in range(N + 1)]

for i in range(1, N + 1):
    for j in range(1, M + 1):
        if str1[i - 1] == str2[j - 1]:
            ary[i][j] = ary[i - 1][j - 1] + 1
        else:
            ary[i][j] = max(ary[i - 1][j], ary[i][j - 1])

print(ary[N][M])
