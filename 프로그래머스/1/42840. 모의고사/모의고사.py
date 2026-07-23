def solution(answers):
    answer = []
    pattern1 = [1, 2, 3, 4, 5]
    pattern2 = [2, 1, 2, 3, 2, 4, 2, 5]
    pattern3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    score = [0, 0, 0]
    
    for idx, ans in enumerate(answers):
        if ans == pattern1[idx % len(pattern1)]:
            score[0] += 1
        if ans == pattern2[idx % len(pattern2)]:
            score[1] += 1
        if ans == pattern3[idx % len(pattern3)]:
            score[2] += 1
            
    max_score = max(score)
    for i, scr in enumerate(score):
        if scr == max_score:
            answer.append(i + 1)
    return answer