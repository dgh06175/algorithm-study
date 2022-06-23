n, m = map(int, input().split())
ary = list(map(int, input().split()))
maxNum = max(ary)
result = 0
# for i in range(maxNum - 1, -1, -1):
#   sum = 0
#   for j in range(n):
#     if ary[j] - i > 0:
#       sum += ary[j] - i
#   if sum >= m:
#     result = i
#     break

start = 0
end = maxNum
while start <= end :
  mid = (start + end) // 2
  sum = 0
  for i in ary:
    if i - mid > 0:
      sum += i - mid
  if sum == m:
    result = mid
    break
  elif sum < m:
    end = mid - 1
  else:
    result = mid
    start = mid + 1
  

print(result)