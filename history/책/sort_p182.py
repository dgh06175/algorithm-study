# sol.1
n, k = map(int, input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))

A.sort()
B.sort(reverse = True)

result = 0
# for v in range(k):
#   result += B[v]

# for v in range(k, n):
#   result += A[v]

for i in range(k):
  if A[i] < B[i]:
    A[i], B[i] = B[i], A[i]

for v in range(n):
  result+=A[v]
  

print(result)

# sol.2

# n, k = map(int, input().split())
# A = list(map(int,input().split()))
# B = list(map(int,input().split()))

# def swap(num):
#   for _ in range(num):
#     min_A_index = 0
#     max_B_index = 0
#     for i in range(n):
#       if A[min_A_index] > A[i]:
#         min_A_index = i
#     for j in range(n):
#       if B[max_B_index] < B[j]:
#         max_B_index = j
#     A[min_A_index], B[max_B_index] = B[max_B_index], A[min_A_index]

# swap(k)

# print(sum(A))