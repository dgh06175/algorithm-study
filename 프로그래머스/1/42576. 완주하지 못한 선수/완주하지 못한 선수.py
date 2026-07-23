def solution(participant, completion):
    participant.sort()
    completion.sort()
    answer = ''
    for i in range(len(participant)):
        if i == len(participant) - 1:
            answer = participant[i]
        elif participant[i] != completion[i]:
            answer = participant[i]
            break
    return answer