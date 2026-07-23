# def solution(n, computers):
#     visit = [False for _ in range(200)]
#     def dfs(node):
#         visit[node] = True
#         for i in range(n):
#             if computers[node][i] == 1 and visit[i] == False:
#                 dfs(i)
                
#     answer = 0
#     for i in range(n):
#         if visit[i] == False:
#             dfs(i)
#             answer += 1
    
#     return answer

def solution(n, computers):
    answer = 0
    visited = [False] * n
    def dfs(node):
        visited[node] = True
        for idx in range(n):
            if computers[node][idx] == 1 and not visited[idx]:
                dfs(idx)

    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer += 1
    return answer







