def solution(A):
  prev, diff = -1, 10 ** 9
  A.sort()
  for a in A:
    if prev != -1:
      diff = min(diff, abs(a - prev))
    prev = a
  return diff


A = [10, 5]
B = [9, 11, 7]
C = [7, 13, 10]
D = [1, 1, 3]
print(solution(A))
print(solution(B))
print(solution(C))
print(solution(D))

# N [2..100,000]
# 요소[1..1,000,000,000]