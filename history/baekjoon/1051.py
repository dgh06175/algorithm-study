n, m = map(int, input().split())

ary = []

for v in range(n):
    ary.append(list(map(int, input())))

answer = 1

for i in range(n):  # i : 세로 시작
    for i_end in range(i + 1, n):  # i_end: 세로 끝
        for j in range(m):  # j : 가로 시작
            for j_end in range(j + 1, m):  # j_end: 가로 끝
                if i_end - i == j_end - j:
                    if (ary[i][j] == ary[i][j_end]) and (ary[i][j_end] == ary[i_end][j]) and (
                            ary[i_end][j] == ary[i_end][j_end]):
                        answer = max(answer, (i_end - i + 1) * (j_end - j + 1))

print(answer)
