def binary_search(array, target, start, end):
  if start > end:
    return None
  mid = (start + end) // 2
  if array[mid] == target:
    return mid
  elif array[mid] < target:
    return binary_search(array, target, mid + 1, end)
  else:
    return binary_search(array, target, start, end - 1)

ary = [0, 11, 43, 2, 95, 33, 22, 72]

ary.sort()
print(ary)

n = int(input())

print(binary_search(ary, n, 0, len(ary) - 1) + 1, "번 방에 있습니다")