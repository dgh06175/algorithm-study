def solution(sizes):
    answer = 0
    for i in range(len(sizes)):
        if sizes[i][0] < sizes[i][1]:
            tmp = sizes[i][0]
            sizes[i][0] = sizes[i][1]
            sizes[i][1] = tmp
    maxWidth = 0
    maxHeight = 0
    for size in sizes:
        if maxWidth < size[0]:
            maxWidth = size[0]
        if maxHeight < size[1]:
            maxHeight = size[1]
    answer = maxWidth * maxHeight
    return answer