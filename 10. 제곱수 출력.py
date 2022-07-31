x, y = map(int, input().split())
# x와 y는 0이상 2^31-1 이하의 정수이다.

# a^n 은 n이 짝수일때 a^(n//2) * a^(n//2) 이고
#        n이 홀수일때 a^(n//2) * a^(n//2) * a 이다


def power(x, y):
  if y == 0:
    return 1
  
  m = power(x, y//2) % 20091024
  
  if y % 2 == 0:
    return (m * m)
    
  else:
    return (m * m * x)
    
print(power(x, y) % 20091024)