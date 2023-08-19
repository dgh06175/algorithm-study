a, b, sign = map(str, input().split())


def roman_to_int(roman):
    integral = 0
    for i in range(len(roman)):
        sign = roman[i]
        next_sign = False
        if i < len(roman) - 1:
            next_sign = roman[i + 1]

        if sign == 'M':
            integral += 1000
        elif sign == 'D':
            integral += 500
        elif sign == 'C':
            if next_sign == 'M' or next_sign == 'D':
                integral -= 100
            else:
                integral += 100
        elif sign == 'L':
            integral += 50
        elif sign == 'X':
            if next_sign == 'L' or next_sign == 'C':
                integral -= 10
            else:
                integral += 10
        elif sign == 'V':
            integral += 5
        elif sign == 'I':
            if next_sign == 'V' or next_sign == 'X':
                integral -= 1
            else:
                integral += 1
    return integral


def int_to_roman(integral):
    roman = []
    if integral >= 1000:
        for _ in range(integral // 1000):
            roman.append('M')
    if integral >= 100:
        a = (integral % 1000) // 100
        if a >= 1 and a <= 3:
            for _ in range(a):
                roman.append('C')
        elif a == 4:
            roman.append('CD')
        elif a >= 5 and a <= 8:
            roman.append('D')
            for _ in range(a - 5):
                roman.append('C')
        elif a == 9:
            roman.append('CM')
    if integral >= 10:
        b = (integral % 100) // 10
        if b >= 1 and b <= 3:
            for _ in range(b):
                roman.append('X')
        elif b == 4:
            roman.append('XL')
        elif b >= 5 and b <= 8:
            roman.append('L')
            for _ in range(b - 5):
                roman.append('X')
        elif b == 9:
            roman.append('XC')

    if integral >= 0:
        c = (integral % 10)
        if c >= 1 and c <= 3:
            for _ in range(c):
                roman.append('I')
        elif c == 4:
            roman.append('IV')
        elif c >= 5 and c <= 8:
            roman.append('V')
            for _ in range(c - 5):
                roman.append('I')
        elif c == 9:
            roman.append('IX')

    return roman


# 645 -> 600 DC 40 XL 5 V
# 2983 -> 2000 MM 900 CM 80 LXXX 3 III
# MMCMLXXXIII
# 100 C
# 200 CC
# 300 CCC
# 400 CD
# 500 D
# 600 DC
# 700 DCC
# 800 DCCC
# 900 CM


def calculator(a, b, sign):
    if sign == '+':
        return a + b
    elif sign == '-':
        return a - b
    elif sign == '*':
        return a * b
    elif sign == '/':
        return a // b
    return False


result = calculator(roman_to_int(a), roman_to_int(b), sign) % 4000

ans = int_to_roman(result)
for i in ans:
    print(i, end='')
