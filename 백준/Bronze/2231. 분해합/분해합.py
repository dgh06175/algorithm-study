def get분해합(a):
    sum = a
    while a > 0:
        sum += a % 10
        a //= 10
    return sum


def getAns(n):
    for i in range(1, n):
        if get분해합(i) == n:
            return i
    return 0


n = int(input())

print(getAns(n))
