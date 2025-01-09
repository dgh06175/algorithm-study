from collections import deque

s, t = map(int, input().split())


def solution(s, t):
    if s == t:
        print(0)
        return

    visited = set()

    dfs(visited, s, t)


# *, +, -, / 순서
def calc(index, n):
    if index == 0:
        return n * n
    if index == 1:
        return n + n
    if index == 2:
        return n - n
    return n / n  # s 0이 아니어야 함


def get_string(index):
    if index == 0:
        return "*"
    if index == 1:
        return "+"
    if index == 2:
        return "-"
    return "/"  # s 0이 아니어야 함


def dfs(visited, n, t):
    queue = deque()
    queue.append([n, ""])
    visited.add(n)

    while queue:
        v, ans = queue.popleft()
        if v == t:
            print(ans)
            break

        for i in range(4):
            if i == 3 and v == 0:
                continue
            x = calc(i, v)
            if x not in visited and x <= 10 ** 9:
                queue.append([x, ans + get_string(i)])
                visited.add(x)
    else:
        print(-1)


solution(s, t)
