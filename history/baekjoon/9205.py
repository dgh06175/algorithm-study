from collections import deque

t = int(input())

for _ in range(t):
    n = int(input())
    home_x, home_y = map(int, input().split())
    store = []
    for _ in range(n):
        store.append(list(map(int, input().split())))
    rock_x, rock_y = map(int, input().split())
    
    q = deque([(home_x, home_y)])
    visited = [False] * n
    while q:
        x, y = q.popleft()
        if abs(x - rock_x) + abs(y - rock_y) <= 1000:
            print("happy")
            break

        for i in range(len(store)):
            if abs(x - store[i][0]) + abs(y - store[i][1]) <= 1000 and not visited[i]:
                q.append((store[i][0], store[i][1]))
                visited[i] = True
    else:
        print("sad")
