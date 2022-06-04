# 가로줄이 행이다. 
# 최소 숫자가 가장 큰 행을 골라서 그 행의 최소숫자 출력

# 세 번째 풀이 (정리)
n, m = map(int, input().split())
result = 0
#num_max = 0 # 각 카드의 숫자의 최소값인 1보다 작게 기본값 설정
for u in range(n):
  #num_max = max(num_max,(min(list(map(int, input().split())))))
  data = list(map(int,input().split()))
  min_value = min(data)
  result = max(min_value, result)
  # 입력받은 한 줄에서의 최소숫자를 result와 비교하여 큰 값을 result에 저장
print(result)

# 두 번째 풀이//////////////////////////////

# ary = []
# for u in range(n):
#   ary.append(list(map(int, input().split())))

# min_nums = list()
# for v in range(n):
#   min_nums.append(min(ary[v]))

# print(max(min_nums))


# 첫 번째 풀이////////////////////////////////

# min_nums = list()
# for v in range(n):
#   min = ary[v][0]
#   for w in range(m):
#     if ary[v][w] < min:
#       min = ary[v][w]
#   min_nums.append(min)

# max = min_nums[0]
# for x in range(n):
#   if min_nums[x] > max:
#     max = min_nums[x]

# print(max)