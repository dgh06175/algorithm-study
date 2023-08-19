from itertools import combinations
#  2 0 0 0 1 1 0
#  0 0 1 0 1 2 0
#  0 1 1 0 1 0 0
#  0 1 0 0 0 0 0
#  0 0 0 0 0 1 1
#  0 1 0 0 0 0 0
#  0 1 0 0 0 0 0
# 1방화벽 2산불 산불은 상하좌우로 퍼짐
# 방화벽 3개를 효율적으로 치면 (!위치에)
#  2 ! 0 0 1 1 2
#  ! 0 1 0 1 2 2
#  0 1 1 0 1 2 2
#  0 1 0 0 0 ! 2
#  0 0 0 0 0 1 1
#  0 1 0 0 0 0 0
#  0 1 0 0 0 0 0
# 불이 모두 퍼지면 이렇게 된다. 이때 안전구역은 27칸이다.
# 안전구역 개수의 최댓값을 구하라.
# 다 해봐도 최대 2억번 정도의 계산임 그냥 할만 함
n, m = map(int, input().split())
# 세로, 가로
world = []
for _ in range(n):
  world.append(list(map(int, input().split())))

answer = 0

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
# i,j 위치에서 불이 번지게 만들고 불이 번진 면적을 cnt를 리턴
# cnt는 초기값을 -1을 넣어줘야 제대로 반환된다.
def dfs(world_map, n, m, i, j, cnt):
  world_map[i][j] = 2
  cnt += 1
  for d in range(4):
    nx = i + dx[d]
    ny = j + dy[d]
    if nx >= 0 and ny >= 0 and nx < n and ny < m:
      if world_map[nx][ny] == 0:
        cnt = dfs(world_map, n, m, nx, ny, cnt)
  return cnt
        
  

# 산불이 전부 확산된 지도로 만들어주고 번진 면적을 리턴하는 함수
def fire_spread(world_map, n, m):
  count = 0
  for i in range(n):
    for j in range(m):
      if world_map[i][j] == 2:
        count += dfs(world_map, n, m, i, j, -1)
  return count

# print("불이 번진 면적은", fire_spread(world, n, m))
# 이제 방화벽 세개를 0위치에 모든 경우의수를 놓아보고, 원래 안전구역에서 불에 번진 총 면적을 빼줘서 안전구역의 최댓값이 몇인지 구하면 된다.

# 모든 경우의 안전구역 세개의 좌표가 나열된 리스트
safe_list = []

for i in range(n):
  for j in range(m):
    if world[i][j] == 0:
      safe_list.append((i, j))

# for i in range(len(safe_list)):
#   print(safe_list[i])

normal_safe_area = len(safe_list)
max_safe_area = 0

for v in list(combinations(safe_list, 3)):
  wall_appended_world = [[0]*m for _ in range(n)]
  for k in range(n):
    for l in range(m):
      wall_appended_world[k][l] = world[k][l]
  for w in range(3):
    wall_appended_world[v[w][0]][v[w][1]] = 1
  fire_added_area = fire_spread(wall_appended_world, n, m)
  safe_area = normal_safe_area - fire_added_area - 3
  if safe_area > max_safe_area:
    max_safe_area = safe_area

# for i in range(n):
#   for j in range(m):
#     print(world[i][j], end='')
#   print()
print(max_safe_area)