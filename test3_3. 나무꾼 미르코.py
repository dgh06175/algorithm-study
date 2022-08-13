# test3_3. 나무꾼 미르코
n, m = map(int, input().split())
ary = list(map(int, input().split()))


# O(N^2) 는 안된다.
# 자를 높이를 찾는 알고리즘을 이진 탐색으로 구현해보자.-> O(NlogN)
# end = 최대 나무의 높이
# start = 0
# mid = (end + start) // 2
# mid 가 m보다 크다면 start = mid
# mid 가 m보다 작다면 end = mid
# mid 가 m이라면 mid 가 정답
# start가 end인데 정답이 안나왔다면 가장 최근에 mid가 m보다 컸을때의 mid가 정답
ary.sort(reverse=True)


# 나무 리스트와 자를 높이를 받아서 얼마나 잘렸는지 리턴하는 함수
def get_wood(wood_ary, cut_height):  # O(N)
    woods = 0
    # wood_ary.sort(reverse=True)
    for wood in wood_ary:
        if cut_height >= wood:
            break
        woods += wood - cut_height
    return woods


def get_answer(wood_ary, target):
    start = 0
    end = max(wood_ary)
    mid = (start + end) // 2
    while start < end:
        mid = (start + end) // 2
        if get_wood(ary, mid) == target:
            return mid
        elif get_wood(ary, mid) > target:
            start = mid
        else:
            end = mid
    
    if start == end:
        if get_wood(ary, mid) < target:
            return mid + 1
        else:
            return mid
    return False

    # cut = wood_ary[0]
    # i = 0
    # while get_wood(wood_ary, cut) < target:
    #     i += 1
    #     cut = wood_ary[i]
    # while get_wood(wood_ary, cut) < target:
    #     cut += 1

    # return cut


ans = get_answer(ary, m)
print(ans)