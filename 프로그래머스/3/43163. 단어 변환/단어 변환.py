from collections import deque

def solution(begin, target, words):
    if not target in words:
        return 0
    words.append(begin)
    begin_index = len(words) - 1
    target_index = words.index(target)
    
    #1. 하나의 알파벳만 다른 단어들끼리 연결되는 그래프 만들기
    graph = [[] for _ in range(len(words))]
    for i in range(len(words)):
        for j in range(i+1, len(words)):
            same_count = 0
            for a in range(len(words[0])):
                if words[i][a] == words[j][a]:
                    same_count += 1
            if same_count == len(words[0]) - 1:
                graph[i].append(j)
                graph[j].append(i)
    
    #2. 그래프를 활용해서 bfs 알고리즘으로 begin에서 target까지의 최단거리 구하기
    # begin은 graph에서 len(words)-1 번 노드이다.
    # target은 graph에서 target_index 번 노드이다.
    
    distance = [1000] * len(graph)
    distance[begin_index] = 0
    queue = deque([begin_index])
    while queue:
        node = queue.popleft()
        for next_node in graph[node]:
            if distance[next_node] > distance[node] + 1:
                queue.append(next_node)
                distance[next_node] = distance[node] + 1
                
    return distance[target_index]