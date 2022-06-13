pos = input()

row = ord(pos[0]) - ord('a') + 1
col = int(pos[1])
count = 0

# dx = [2, 2, 1, -1, -2, -2, 1, -1]
# dy = [1, -1, 2, 2, 1, -1, -2, -2]

# for i in range(8):
#   nx = row + dx[i]
#   ny = col + dy[i]
#   if nx >= 1 and nx <= 8 and ny >= 1 and ny <= 8:
#     count+=1

# print(count)

step = [[2,1],[2,-1],[1,2],[-1,2],[-2,1],[-2,-1],[1,-2],[-1,-2]]

for steps in step:
  nrow = row + steps[0]
  ncol = col + steps[1]
  if nrow >=1 and nrow <= 8 and ncol >= 1 and ncol <= 8:
    count+=1

print(count)