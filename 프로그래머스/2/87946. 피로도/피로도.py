# from itertools import permutations

# def solution(k, dungeons):
#     answer = -1
#     for order_tuple in permutations([v for v in range(len(dungeons))]):
#         tire_rate = k
#         complete = 0
#         for i in order_tuple:
#             if dungeons[i][0] <= tire_rate:
#                 complete += 1
#                 tire_rate -= dungeons[i][1]
#         answer = max(answer, complete)
#     return answer

answer = 0
N = 0
visited = []


def dfs(k, cnt, dungeons):
    global answer
    if cnt > answer:
        answer = cnt

    for j in range(N):
        if k >= dungeons[j][0] and not visited[j]:
            visited[j] = 1
            print(dungeons[j])
            dfs(k - dungeons[j][1], cnt + 1, dungeons)
            visited[j] = 0


def solution(k, dungeons):
    global N, visited
    N = len(dungeons)
    visited = [0] * N
    dfs(k, 0, dungeons)
    return answer