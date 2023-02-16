def solution(N):
  alpha = "abcdefghijklmnopqrstuvwxyz"
  if N <= 26:
    return alpha[:N]
  else:
    for i in range(2, 27):
      if N % i == 0:
        return alpha[:(N//i)] * i
  return None


print(solution(3))
print(solution(5))
print(solution(30))
print(len(solution(33)))
print(len(solution(100)))