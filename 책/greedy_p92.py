# page 92
n, m, k = map(int, input().split())
ary = list(map(int,input().split()))

ary.sort() # 작은 순으로 정렬

first = ary[len(ary)-1] # 가장 큰 수
second = ary[len(ary)-2] # 두 번째로 큰 수

mTE = (first * k + second) # mTE는 4 4 1
count = m // (k + 1) # mTE의 개수

result = mTE * (count) # mTE 들 먼저 계산
result += first * (m - (count * (k + 1)))#나머지 뒤에 붙은 수 계산
print(result)



# m = 전체개수 k = 2
# 4 
# 4 4 
# 4 4 1
# 4 4 1 4
# 4 4 1 4 4
# 4 4 1 4 4 1