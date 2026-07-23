def solution(prices):
    answer = [0] * len(prices)
    stack = []
    for idx, p in enumerate(prices):
        while stack and stack[-1][1] > p:
            i, _ = stack.pop()
            answer[i] = idx - i
        stack.append([idx, p])
        
    for i, _ in stack:
        answer[i] = len(prices) - 1 - i
    return answer

# 하나하나 다하면 O(N^2) 이라 안된다.
# price 보다 낮은 수가 나올때와의 인덱스 차이를 구하면 된다. (없다면 마지막 순서와의 인덱스 차이)
# p