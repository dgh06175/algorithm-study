n = int(input())
n_ary = list(map(int, input().split()))
q = int(input())
q_ary = list(map(int, input().split()))

def binarySearch(ary, low, high, target):
  while low <= high:
    mid = (low + high) // 2
    if ary[mid] == target:
      return mid
    elif ary[mid] > target:
      high = mid-1
    else:
      low = mid+1
  return -1

for i in q_ary:
  print(binarySearch(n_ary, 0, len(n_ary), i), end=' ')