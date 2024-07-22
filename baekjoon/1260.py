from collections import deque


def dfs(graph, node, visited_dfs):
    visited_dfs[node] = True
    print(node, end=" ")
    for next_node in graph[node]:
        if not visited_dfs[next_node]:
            dfs(graph, next_node, visited_dfs)


def bfs(graph, start, visited_bfs):
    queue = deque([start])
    visited_bfs[start] = True
    print(start, end=" ")
    while queue:
        node = queue.popleft()
        for next_node in graph[node]:
            if not visited_bfs[next_node]:
                queue.append(next_node)
                visited_bfs[next_node] = True
                print(next_node, end=" ")


n, m, v = map(int, input().split())

G = [[] for i in range(1001)]

for _ in range(m):
    a, b = map(int, input().split())
    G[a].append(b)
    G[b].append(a)

for K in G:
    K.sort()

visited = [False] * 1001
dfs(G, v, visited)
print()
visited = [False] * 1001
bfs(G, v, visited)
