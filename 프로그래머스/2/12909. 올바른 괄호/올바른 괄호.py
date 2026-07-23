def solution(s):
    answer = True
    cnt = 0
    
    for v in s:
        if v == '(':
            cnt += 1
        else:
            cnt -= 1
        if cnt < 0:
            return False
    if cnt != 0:
        return False
    return True