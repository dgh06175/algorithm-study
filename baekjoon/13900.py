n = int(input())
arr = list(map(int, input().split()))
ans = 0
# arr_sum = sum(arr)
#
# for a in arr:
#     sums = arr_sum - a
#     ans += sums * a
#
# print(ans // 2)
# print(int(ans / 2))

arr_sum = sum(arr)
sums = 0

for i in range(n):
    sums += arr[i]
    tmp = arr_sum - sums
    ans += arr[i] * tmp

print(ans)
