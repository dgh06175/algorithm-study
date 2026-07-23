def solution(players, callings):
    dic_key = dict()
    dic_value = dict()
    for i in range(len(players)):
        dic_key[players[i]] = i + 1
        dic_value[i + 1] = players[i]
    
    for c in callings:
        origin_num = dic_key[c]
        dic_key[c] -= 1
        dic_key[dic_value[origin_num-1]] += 1
        dic_value[origin_num - 1], dic_value[origin_num] = dic_value[origin_num], dic_value[origin_num - 1]
        
    a = dict(sorted(dic_key.items(), key = lambda x:x[1]))
    return list(a.keys())