def solution(n, lost, reserve):
    # 체육복을 도난당한 학생과 여벌의 체육복을 가져온 학생을 구분하여 리스트로 만듦
    lost_set = set(lost) - set(reserve)
    reserve_set = set(reserve) - set(lost)
    
    # 여벌의 체육복을 가진 학생이 체육복을 도난당한 경우를 처리하기 위해 두 리스트에서 중복되는 번호를 제거
    for r in reserve_set.copy():
        if r-1 in lost_set:
            reserve_set.remove(r)
            lost_set.remove(r-1)
        elif r+1 in lost_set:
            reserve_set.remove(r)
            lost_set.remove(r+1)
    
    # 체육복을 빌려줄 수 있는 학생들을 찾아 체육수업을 들을 수 있는 학생 수를 계산
    students = [1] * n
    for l in lost_set:
        students[l-1] = 0
    for r in reserve_set:
        students[r-1] = 2
    
    for i in range(n):
        if students[i] == 0:
            if i > 0 and students[i-1] == 2:
                students[i] = 1
                students[i-1] = 1
            elif i < n-1 and students[i+1] == 2:
                students[i] = 1
                students[i+1] = 1
    
    return sum(1 for s in students if s > 0)