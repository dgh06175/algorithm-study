import math

x, y = map(int, input().split())

g = math.gcd(x, y)
print(g)
l = math.lcm(x, y)
print(l)
