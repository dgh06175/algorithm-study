from collections import deque


def dfs(graph, visited, node):
    print(node, end=' ')
    visited[node] = True
    for next_node in graph[node]:
        if not visited[next_node]:
            dfs(graph, visited, next_node)


def bfs(graph, visited, start):
    queue = deque([start])
    visited[start] = True
    while queue:
        node = queue.popleft()
        print(node, end=' ')
        for next_node in graph[node]:
            if not visited[next_node]:
                queue.append(next_node)
                visited[next_node] = True


n, m, v = map(int, input().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for g in graph:
    g.sort()

visited = [False] * (n + 1)

dfs(graph, visited[:], v)
print()
bfs(graph, visited[:], v)
