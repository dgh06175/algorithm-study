def gcd(a, b):
    if b == 0: return a
    return gcd(b, a % b)


def lcm(a, b):
    return a / gcd(a, b) * b


a, b = map(int, input().split())

print(gcd(a, b))
print(int(lcm(a, b)))
