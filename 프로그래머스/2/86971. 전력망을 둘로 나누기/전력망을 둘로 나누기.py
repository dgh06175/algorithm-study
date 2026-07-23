import copy

def dfs(graph, node, check, count):
    check[node] = True
    count[0] += 1
    for v in graph[node]:
        if not check[v]:
            dfs(graph, v, check, count)


def solution(n, wires):
    answer = n
    
    for rm in range(0, len(wires)):
        graph = [[]for _ in range(n+1)]
        wires_tmp = copy.deepcopy(wires)
        wires_tmp.pop(rm)
    
        for w in wires_tmp:
            graph[w[0]].append(w[1])
            graph[w[1]].append(w[0])
            
        print(graph)
        ans = []
        check = [False] * (n+1)
        for v in range(1, n+1):
            if check[v] == False and graph[v]:
                count = [0]
                dfs(graph, v, check, count)
                ans.append(count[0])
                
        if ans and ans[0] == n - 1:
            ans.append(1)
            
        if len(ans) == 2:
            answer = min(answer, abs(ans[0] - ans[1]))
    return answer