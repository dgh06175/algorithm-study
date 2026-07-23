def solution(routes):
    routes.sort(key=lambda x: x[1])  # 진출 지점을 기준으로 오름차순 정렬
    camera_pos = -30001  # 카메라의 위치 초기값 (가장 왼쪽 지점)
    answer = 0
    
    for route in routes:
        if route[0] > camera_pos:  # 카메라 위치보다 앞에 있는 차량이라면
            camera_pos = route[1]  # 새로운 카메라를 설치하고 카메라 위치 갱신
            answer += 1
    
    return answer
