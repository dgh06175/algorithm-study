import heapq


class ShortestPath:
    graph = []
    n = 0


    def __init__(self, input_graph):
        self.graph = input_graph
        self.n = len(self.graph)

    def print_graph(self):
        print("### 그래프 정보 출력 ###")
        print("데이터 형식 : [연결 노드, 길이]")
        for i in range(1, self.n):
            print(f"{i}번 노드 : {self.graph[i]}")
        print()

    def print_distance(self, start):
        print(f"### 노드 {str(start)}에서 다른 노드까지의 최단 거리 ###")
        distance = self.dijkstra(start)
        for i in range(1, self.n):
            print(i, end=" ")
        print()
        for i in range(1, self.n):
            print(distance[i], end=" ")
        print('\n')


    def dijkstra(self, start):
        queue = []
        heapq.heappush(queue, (start, 0))
        dis = [10**9] * self.n
        dis[start] = 0
        while queue:
            node, cost = heapq.heappop(queue)
            if dis[node] < cost:
                continue
            for n, c in graph[node]:
                cost_sum = cost + c
                if cost_sum < dis[n]:
                    dis[n] = cost_sum
                    heapq.heappush(queue, (n, cost_sum))
        return dis

    def print_shortest_path(self, start, end):
        distance = self.dijkstra(start)
        if distance[end] == 10**9:
            print("길이 없습니다.")
            return
        path = []
        current = end
        while current != start:
            path.append(current)
            for node, cost in self.graph[current]:
                if distance[current] == distance[node] + cost:
                    current = node
                    break
        path.append(start)

        print(f"### {str(start)}에서 {str(end)}까지의 최단 경로 ###")
        path.reverse()
        path = map(str, path)
        print("->".join(path))
        print(f"최단경로의 길이 : {str(distance[end])}")


graph = [
    [],
    [[2, 2], [5, 7]],
    [[1, 2], [3, 2], [4, 3], [5, 1]],
    [[2, 2], [4, 1]],
    [[2, 3], [3, 1], [5, 7], [6, 7]],
    [[1, 7], [2, 1], [4, 7]],
    [[4, 7]],
]


shortestPath = ShortestPath(graph)
shortestPath.print_graph()

a = int(input("어떤 노드부터 다른 노드들 까지의 최단 거리를 보시겠습니까? (1 ~ 6) : "))
shortestPath.print_distance(a)

b, c = map(int, input("어떤 노드부터 어떤 노드까지의 최단 경로를 보시겠습니까? (1 ~ 6 사이 값 공백으로 구분하여 두개 입력) : ").split())
shortestPath.print_shortest_path(b, c)

