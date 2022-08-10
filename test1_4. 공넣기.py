from copy import deepcopy

n, m = map(int, input().split())
world = []
for _ in range(n):
    world.append(list(input()))

direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]


def myFunc(board, direct, count, W, B):
    if count > 10:  # 실패
        return -1

    if count != 0:  # 맨 처음 경우가 아니라면
        Wtmp = W
        Btmp = B
        tmp = -1
        nx = direction[direct][0]
        ny = direction[direct][1]
        if board[B[0] + nx][B[1] + ny] == 'O':
            return -1
        while board[B[0] + nx][B[1] + ny] == '.':
            B[0] += nx
            B[1] += ny
            if board[B[0] + nx][B[1] + ny] == 'O':
                return -1

        if board[W[0] + nx][W[1] + ny] == 'O':
            tmp = count
        while board[W[0] + nx][W[1] + ny] == '.':
            W[0] += nx
            W[1] += ny
            if board[W[0] + nx][W[1] + ny] == 'O':
                tmp = count

        if board[B[0] + nx][B[1] + ny] == 'O':
            return -1
        while board[B[0] + nx][B[1] + ny] == '.':
            B[0] += nx
            B[1] += ny
            if board[B[0] + nx][B[1] + ny] == 'O':
                return -1

        if tmp != -1:
            return tmp

        board[Wtmp[0]][Wtmp[1]] = '.'
        board[Btmp[0]][Btmp[1]] = '.'
        board[W[0]][W[1]] = 'W'
        board[B[0]][B[1]] = 'B'

    board_tmp = deepcopy(board)
    #board_tmp = board
    min = 100
    for i in range(4):
        temp = myFunc(board_tmp, i, count + 1, W, B)
        if temp != -1 and min > temp:
            min = temp

    return min


W = [0, 0]
B = [0, 0]
for i in range(n):
    for j in range(m):
        if world[i][j] == 'W':
            W[0], W[1] = i, j
        if world[i][j] == 'B':
            B[0], B[1] = i, j

print(myFunc(world, 0, 0, W, B))
