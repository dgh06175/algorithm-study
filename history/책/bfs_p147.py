from collections import deque

def bfs(graph, start, visited):
  queue = deque([start])
  visited[start] = True # 현재 노드를 방문처리
  while queue: # 큐가 빌때까지 반복
    v = queue.popleft() # 큐에서 하나의 원소를 뽑아서 출력
    print(v, end=' ')
    for i in graph[v]: # 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
      if not visited[i]:
        if not visited[i]:
          queue.append(i)
          visited[i] = True

# 각 노드가 연결된 정보를 인접 리스트로 표현(2차원 리스트)
graph = [
	[],        # 0
	[2, 3, 8], # 1 번 노드와 연결된 노드
	[1, 7],    # 2
	[1, 4, 5], # 3
	[3, 5],    # 4
	[3, 4],    # 5
	[7],       # 6
  [2, 6, 8],    # 7
  [1, 7]     # 8
]

visited = [False] * 9 # 각 노드의 방문 정보를 포현현

bfs(graph, 1, visited) # 정의된 BFS 함수 호출