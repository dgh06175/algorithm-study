import sys

input = sys.stdin.readline

stack = []

n = int(input())

for _ in range(n):
    tmp = sys.stdin.readline().split()
    if tmp[0] == "push":
        stack.append(int(tmp[1]))

    if tmp[0] == "pop":
        if not stack:
            print(-1)
        else:
            print(stack.pop())

    if tmp[0] == "size":
        print(len(stack))

    if tmp[0] == "empty":
        if stack:
            print(0)
        else:
            print(1)

    if tmp[0] == "top":
        if not stack:
            print(-1)
        else:
            print(stack[-1])
