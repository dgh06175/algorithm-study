n = int(input())
nAry = sorted(list(map(int, input().split())))
m = int(input())
mAry = list(map(int, input().split()))
# nAry = []
# mAry = []
# for i in range(1000000):
#   nAry.append(i)

# for j in range(10000):
#   mAry.append(j)

def binary_search(array, target, start, end):
  while start <= end:
    mid = (start + end) // 2
    if array[mid] == target:
      return True
    elif array[mid] < target:
      start = mid + 1
    else:
      end = mid - 1
  return False

for t in mAry:
  if binary_search(nAry, t, 0, n - 1):
  #if t in nAry:
    print("yes", end=' ')
  else:
    print("no", end=' ')

