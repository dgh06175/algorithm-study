n = int(input())
ary = list(map(int, input()))
first_cow = 0  # 첫 번째 소의 위치
last_cow = 0  # 마지막 소의 위치
cow_count = 0  # 소의 마릿수
gapAry = []  # 소끼리의 간격을 넣을 배열
for i in range(n):
    if ary[i] == 1:
        cow_count += 1
        last_cow = i
for i in range(n):
    if ary[i] == 1:
        first_cow = i
        break
before_cow = first_cow
for i in range(first_cow + 1, n):
    if ary[i] == 1:
        gapAry.append(i - before_cow)
        before_cow = i

first_gap = first_cow  # 맨 왼쪽과 다음 소의 거리
last_gap = n - 1 - last_cow  # 맨 오른쪽과 이전 소의 거리
#print(first_gap, last_gap)

ans = []
if cow_count == 0:  # 소가 없을때
    print(n - 1)
# 000010
elif cow_count == 1:
    # 맨 왼쪽에 몰아넣을때
    # 맨 오른쪽에 몰아넣을때
    # 양쪽에 하나씩 넣을때
    ans.append(first_gap // 2)
    ans.append(last_gap // 2)
    ans.append(min(first_gap, last_gap))
    print(max(ans))

elif cow_count == 2:  # 소가 두마리일때
    # 왼쪽에 몰아넣을 때
    # 오른쪽에 몰아넣을 때
    # 가운데에 몰아넣을 때
    # 나눠서 넣을 때
    ans.append(min(first_gap // 2, gapAry[0]))
    ans.append(min(gapAry[0], last_gap // 2))
    ans.append(gapAry[0] // 3)
    ans.append(min(first_gap, gapAry[0] // 2))
    ans.append(min(gapAry[0] // 2, last_gap))
    ans.append(min(first_gap, last_gap))
    print(max(ans))

elif cow_count > 2:  # 소가 세마리 이상일때
    # 왼쪽에 몰아넣을 때
    ans.append(min(first_gap // 2, min(gapAry)))
    # 오른쪽에 몰아넣을 때
    ans.append(min(min(gapAry), last_gap // 2))
    # 양끝에 넣을 때
    ans.append(min(first_gap, min(gapAry), last_gap))

    tmp = gapAry
    tmp.sort(reverse=True)
    tmp[0] = tmp[0] // 2

    # 왼쪽만 넣을 때
    ans.append(min(first_gap, min(tmp)))
    # 오른쪽만 넣을 때
    ans.append(min(min(tmp), last_gap))
    # 맨 끝 모두 안들어갈 때
    tmp[1] = tmp[1] // 2
    ans.append(min(tmp))
    print(max(ans))
