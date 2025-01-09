n = int(input())

for _ in range(n):
    check = [0] * 26
    s = list(input())
    for v in s:
        index = ord(v) - ord('a')
        check[index] += 1
    print(check)

# TODO: 풀다 말음
