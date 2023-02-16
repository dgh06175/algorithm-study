def hanoi(n, start, end, mid, ary, cnt):
    if n == 1:
        ary.append([start, end])
        cnt[0] += 1
        return
    hanoi(n - 1, start, mid, end, ary, cnt)
    ary.append([start, end])
    cnt[0] += 1
    hanoi(n - 1, mid, end, start, ary, cnt)


n = int(input())

cnt = [
    0,
]
if n <= 20:
    ary = []
    hanoi(n, 1, 3, 2, ary, cnt)

    print(cnt[0])
    for i in range(cnt[0]):
        print(ary[i][0], ary[i][1])
else:
    print(pow(2, n) - 1)
