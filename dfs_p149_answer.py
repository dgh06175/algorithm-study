n, m = map(int, input().split())

world = []
for _ in range(n):
  world.append(list(map(int, input())))

dx = [1, 0, -1 ,0]
dy = [0, -1, 0, 1]

def dfs(x, y):
  if x < 0 or y < 0 or x >= n or y >= m:
    return False
  if world[x][y] == 0:
    world[x][y] = 1
    for i in range(4):
      dfs(x + dx[i], y + dy[i])
    return True
  return False
  
result = 0
for v in range(n):
  for w in range(m):
    if dfs(v,w):
      result += 1

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