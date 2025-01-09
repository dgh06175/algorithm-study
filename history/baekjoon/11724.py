from collections import deque

n, m = map(int, input().split())

G = [[] for i in range(1001)]

for _ in range(m):
    u, v = map(int, input().split())
    G[u].append(v)
    G[v].append(u)

visited = [False] * (n + 1)


def dfs(G, node, visited):
    visited[node] = True
    for next_node in G[node]:
        if not visited[next_node]:
            dfs(next_node)


def bfs(G, start, visited):
    q = deque([start])
    visited[start] = True
    q.append(start)

    while q:
        node = q.popleft()
        for next_node in G[node]:
            if not visited[next_node]:
                q.append(next_node)
                visited[next_node] = True


ans = 0
for i in range(n):
    if G[i]:
        if not visited[i]:
            ans += 1
            bfs(G, i, visited)

print(ans)
