def solution(players, callings):
    dic_key = dict()
    dic_value = dict()
    for i in range(len(players)):
        dic_key[players[i]] = i + 1
        dic_value[i + 1] = players[i]

    for c in callings:
        origin_num = dic_key[c]
        dic_key[c] -= 1
        dic_key[dic_value[origin_num - 1]] += 1
        dic_value[origin_num - 1], dic_value[origin_num] = dic_value[origin_num], dic_value[origin_num - 1]

    a = dict(sorted(dic_key.items(), key=lambda x: x[1]))
    return list(a.keys())

# 딕셔너리를 두개 이용해서 key, value 둘 다 서치할때 O(1)로 가능하다.
# sorted의 key 파라미터를 사용해서 특정 값으로 정렬이 가능하다.