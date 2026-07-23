def solution(citations):
    answer = 0
    citations.sort(reverse = True)
    for h in range(len(citations), -1, -1):
        isBigger = True
        for i in range(h):
            if citations[i] < h:
                isBigger = False
        if isBigger:
            return h
    return -1