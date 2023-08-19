from collections import deque

n, m, v = map(int, input().split())
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)

print(graph)


def bfs(graph, start, visited):
  queue = deque([start])
  visited[start] = True # 현재 노드를 방문처리
  while queue: # 큐가 빌때까지 반복
    v = queue.popleft() # 큐에서 하나의 원소를 뽑아서 출력
    print(v, end=' ')
    for i in graph[v]: # 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
      if not visited[i]:
        queue.append(i)
        visited[i] = True


visit = [False] * (n + 1)

bfs(graph, v, visit)
