n = int(input())
moves = input().split()
x = y = 1

move_types = ['L','R','U','D']
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

for move in moves:
  for i in range(len(move_types)):
    if move == move_types[i]:
      nx = x + dx[i]
      ny = y + dy[i]
  if nx < 1 or nx > n or ny < 1 or ny > n:
    continue
  x, y = nx, ny
print(x, y)

# for move in moves:
#   if move == 'l':
#     if y != 1:
#       y -= 1
#   elif move == 'r':
#     if y != n:
#       y += 1
#   elif move == 'u':
#     if x != 1:
#       x -= 1
#   elif move == 'd':
#     if x != n:
#       x += 1
# print(x, y)