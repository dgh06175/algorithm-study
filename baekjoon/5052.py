import sys


def foo(n):
    numbers = []
    for _ in range(n):
        numbers.append(sys.stdin.readline().strip())  # 시간 줄이기 위해 readline 사용
    numbers.sort()
    for i in range(n - 1):
        length = len(numbers[i])

        # 정렬했으므로 바로 다음 원소랑만 비교해도 된다, 리스트 슬라이스로 앞부분만 비교.
        if numbers[i] == numbers[i + 1][:length]:
            return "NO"
    return "YES"


t = int(input())
for _ in range(t):
    n = int(input())
    print(foo(n))
