# https://school.programmers.co.kr/learn/courses/30/lessons/258711
def solution(edges):
    answer = [0, 0, 0, 0]
    made_node = -1

    max_node_number = 0
    for edge in edges:
        for v in edge:
            max_node_number = max(max_node_number, v)

    node_out = [[] for _ in range(max_node_number + 1)]
    node_in = [[] for _ in range(max_node_number + 1)]
    for edge in edges:
        node_out[edge[0]].append(edge[1])
        node_in[edge[1]].append(edge[0])

    for i in range(len(node_out)):
        if len(node_out[i]) >= 2 and not node_in[i]:
            made_node = i
            break

    answer[0] = made_node
    start_node_count = len(node_out[made_node])

    node_out[made_node] = []
    for i in range(len(node_in)):
        if made_node in node_in[i]:
            node_in[i].remove(made_node)

    for i in range(len(node_out)):
        if not node_out[i] and i != made_node and i != 0:
            answer[2] += 1
        if len(node_out[i]) == 2 and len(node_in[i]) == 2:
            answer[3] += 1

    answer[1] = start_node_count - answer[2] - answer[3]
    return answer

    # 나가는 간선이 두개 이상이고 들어오는 간선이 없으면 생성한 정점
    # 그 정점의 간선 개수 = 그래프 개수

    # 도넛
    # 갈수있는 노드로 계속 가는데 경우의 수는 하나이고, 원점으로 돌아옴

    # 막대
    # 갈수있는 노드로 계속 가는데 갈 곳 없는 노드에 도착함
    # 갈 곳 없는 노드 개수 == 막대 그래프 개수

    # 8자
    # 갈수 있는 노드가 여러개일 수 있음 (위에 구하고 빼도 됨) 어떤 길을 선택해도 결국 원점으로 돌아옴.
    # 갈곳 2개 올곳 2개 노드 개수 == 8자 그래프 개수