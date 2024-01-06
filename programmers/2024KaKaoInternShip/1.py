# https://school.programmers.co.kr/learn/courses/30/lessons/258712
def solution(friends, gifts):
    선물받는개수 = {}
    선물지수 = {}

    for friend in friends:
        선물지수[friend] = 0
        선물받는개수[friend] = 0

    for gift in gifts:
        줌, 받음 = gift.split(" ")
        선물지수[줌] += 1
        선물지수[받음] -= 1

    for i in range(len(friends)):
        for j in range(i + 1, len(friends)):
            A = friends[i]
            B = friends[j]
            A_give = 0
            B_give = 0

            for gift in gifts:
                줌, 받음 = gift.split(" ")
                if 줌 == A and 받음 == B:
                    A_give += 1
                if 줌 == B and 받음 == A:
                    B_give += 1

            if A_give > B_give:
                선물받는개수[A] += 1
            elif A_give < B_give:
                선물받는개수[B] += 1
            else:
                if 선물지수[A] > 선물지수[B]:
                    선물받는개수[A] += 1
                elif 선물지수[A] < 선물지수[B]:
                    선물받는개수[B] += 1

    return max(선물받는개수.values())