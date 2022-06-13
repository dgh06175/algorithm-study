def factorial1(n):
  if n <= 1:
    return 1
  else:
    return n * factorial1(n-1)


def factorial2(n):
  result = 1
  for i in range(1, n + 1):
    result *= i
  return result



x = int(input())
print(factorial1(x))
print(factorial2(x))
