def solution(A):
  if len(A) == 1 or len(A) == 2:
    return 1
  answer = 0
  min_hole = min(A)
  max_hole = max(A)
  mid = (max_hole - min_hole) / 2
  B = []
  for i in range(len(A)):
    A[i] = A[i] - min_hole
  for i in A:
    B.append(abs(i-mid))
  answer = int(mid - min(B))
  
  return answer
  


A = [11, 20, 15]
B = [15, 20, 9, 11]
C = [0, 44, 32, 30, 42, 18, 34, 16, 35]
D = [9]
E = [1, 2]
F = [1, 2, 3]
print(solution(A))
print(solution(B))
print(solution(C))
print(solution(D))
print(solution(E))
print(solution(F))
# 최소 구멍과 최대 구멍 사이의 길이를 구하고
# 절반으로 나누고
# 그 값에서 가장 가까운 중간값을 찾는다.
# 그 값이 정답일것이다.
