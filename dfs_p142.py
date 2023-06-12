def dfs(graph, v, visited):
	visited[v] = True # 현재 노드를 방문처리
	print(v, end=' ')
	for i in graph[v]: # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
		if not visited[i]:
			dfs(graph, i, visited)

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

visited = [False] * 9


dfs(graph, 1, visited)
