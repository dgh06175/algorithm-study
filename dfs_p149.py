n, m = map(int, input().split())

world = [[] for _ in range(n)]
for i in range(n):
  world[i] = list(map(int, input()))
  
def dfs(x, y):
  visited[x][y] = True
  for i in range(4):
    nx = x + dx[i]
    ny = y + dy[i]
    if nx >= 0 and nx < n and ny >= 0 and ny < m:
      if world[nx][ny] == 0 and not visited[nx][ny]:
        dfs(nx, ny)
  
dx = [1, 0, -1 ,0]
dy = [0, -1, 0, 1]

visited = [[False] * m for _ in range(n)]

result = 0

for v in range(n):
  for w in range(m):
    if world[v][w] == 0 and not visited[v][w]:
      result += 1
      dfs(v, w)

print(result)

# 15 14
# 00000111100000
# 11111101111110
# 11011101101110
# 11011101100000
# 11011111111111
# 11011111111100
# 11000000011111
# 01111111111111
# 00000000011111
# 01111111111000
# 00011111111000
# 00000001111000
# 11111111110011
# 11100011111111
# 11100011111111

# 7 7
# 0000000
# 1111110
# 0000010
# 0111010
# 0100010
# 0111110
# 0000000