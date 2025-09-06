N = int(input())
answer = 0
while N >= 0:
  if N % 5 == 0:
    answer += N // 5
    break
  N -= 3
  answer += 1
  if N < 0:
    answer = -1
    break

print(answer)