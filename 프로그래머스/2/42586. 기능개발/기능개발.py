# from collections import deque
# def solution(progresses, speeds):
#     answer = []
#     progresses = deque(progresses)
#     speeds = deque(speeds)
#     day = 0
#     count = 0
#     while progresses:
#         if progresses[0] + (speeds[0] * day) >= 100:
#             progresses.popleft()
#             speeds.popleft()
#             count += 1
#         else:
#             if count > 0:
#                 answer.append(count)
#                 count = 0
#             day += 1
#     answer.append(count)
#     return answer

def solution(progresses, speeds):
    answer = []
    length = len(progresses)
    
    for day in range(1, 101):
        notReleasedIndex = -1
        for i in range(length):
            if progresses[i]: # 배포 안된거라면
                if notReleasedIndex == -1:
                    notReleasedIndex = i
                progresses[i] += speeds[i]
        # print(progresses, notReleasedIndex)
        
        releaseCount = 0
        for i in range(notReleasedIndex, length):
            if progresses[i] < 100:
                break
            progresses[i] = False
            releaseCount += 1
        
        if releaseCount > 0:
            answer.append(releaseCount)
        
        isAllReleased = True
        for v in progresses:
            if v != False:
                isAllReleased = False
        if isAllReleased:
            break
            
    return answer

# for문으로 하루씩 지난다.
# 매일 progresses의 각 요소에 speeds를 더한다.
# 100 이상이 되면 progresses의 배포가 안된 작업 부터 맨 앞에서부터 100이상의 진행도를 가진 작업들이 연속으로 몇개가 나오는지 카운트한다.
# 그 수가 1 이상이면 그 수를 answer에 집어넣고, 100 이상의 진행도였던 수들을 False(배포됐다는 뜻)로 저장한다.
# 그 수가 0이면 다음날로 넘어간다.
# progresses의 모든 수가 False가 되면 종료.