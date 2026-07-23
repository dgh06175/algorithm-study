from collections import deque

def solution(bridge_length, weight, truck_weights):
    timer = 0
    bridge = deque([0] * bridge_length)
    trucks = deque(truck_weights)
    bridge_sum = 0
    
    while bridge:
        timer += 1
        bridge_sum -= bridge.popleft()
        if len(trucks) > 0:
            if bridge_sum + trucks[0] <= weight:
                bridge_sum += trucks[0]
                bridge.append(trucks.popleft())
            else:
                bridge.append(0)
        
    return timer

# 각 트럭은 다리 위에서 1씩 이동할 수 있다.
# bridge의 초기값은 0 * bridge_length
# 매 초마다 bridge는 popleft가 되고, 그 값이 0이 아니면 clear+=1이다.
# 매 초마다 bridge는 무게검사를 진행해서 새로운 트럭이 들어오거나 0이 들어온다.
