ary = list(input())
bomb = list(input())

stack = []

for i in range(len(ary)):
    stack.append(ary[i])
    # print(stack)
    if len(stack) < len(bomb):
        continue
    else:
        if stack[len(stack) - len(bomb):] == bomb:  # stack 내부에서 마지막 원소들이 폭탄인지 검사
            del stack[len(stack) - len(bomb):]  # del 하고 배열 인덱스 넣어주면 인덱스로 해당 원소들을 삭제함

if not stack:
    print("FRULA")
else:
    print("".join(stack))
