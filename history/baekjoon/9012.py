T = int(input())


def foo():
    # count = 0
    stack = []
    vps = list(input())
    for item in vps:
        if item == '(':
            stack.append(item)
        if item == ')':
            if len(stack) <= 0:
                return False
            stack.pop()
    if not stack:
        return True
    #     if count < 0:
    #         return False
    #     if item == '(':
    #         count += 1
    #     if item == ')':
    #         count -= 1
    #     else:
    #         return False
    # if count == 0:
    #     return True
    # return False


for _ in range(T):
    if foo():
        print("YES")
    else:
        print("NO")
