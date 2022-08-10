from collections import deque

n, k = map(int, input().split())
durability = deque(list(map(int,input().split())))
isPeople = deque([False]*2*n)

def rotation(queue): # queue의 맨 끝값을 맨 앞으로 가져오는 함수
  queue.appendleft(queue.pop())
  return queue

ans = 0
while not durability.count(0) >= k:
  ans += 1
  rotation(durability) # 회전
  rotation(isPeople)
  isPeople[n-1] = False # 내리는 칸에 있는사람 내림
  # 한칸 앞으로 이동할 수 있는사람들 이동
  for i in range(n-2, 0, -1):
    if isPeople[i] and not isPeople[i+1] and durability[i+1] >= 1:
      isPeople[i] = False
      isPeople[i+1] = True
      durability[i+1] -= 1
  if durability[0] >= 1:
    durability[0] -= 1
    isPeople[0] = True

print(ans)