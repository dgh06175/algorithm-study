# from collections import deque
# def solution(priorities, location):
#     answer = 0
#     queue = deque(priorities)
#     while queue:
#         if queue[0] == max(queue):
#             queue.popleft()
#             answer += 1
#             if location == 0:
#                 return answer
#             location -= 1
#         else:
#             queue.append(queue.popleft())
#             if location == 0:
#                 location = len(queue) - 1
#             else:
#                 location -= 1
#     return -1


from collections import deque
def solution(priorities, location):
    answer = 0
    queue = deque(priorities)
    while (queue):
        max_pri = max(queue)
        J = queue.popleft()
        if J == max_pri:
            answer += 1
            if location == 0:
                return answer
            location -= 1
        else:
            queue.append(J)
            if location == 0:
                location = len(queue) - 1
            else:
                location -= 1

# priorities 는 1~100개
# priorities의 요소인 중요도는 1~9











