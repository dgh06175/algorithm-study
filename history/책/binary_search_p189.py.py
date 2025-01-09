def binary_search(array, target, start, end):
  while start <= end:
    mid = (start + end) // 2
    if array[mid] == target:
      return mid
    elif array[mid] > target:
      end = mid - 1
    else:
      start = mid + 1
  return None


ary = [2, 11, 29, 53, 58, 88, 91]
target = 53
result = binary_search(ary, target, 0, len(ary)-1)
print(result)