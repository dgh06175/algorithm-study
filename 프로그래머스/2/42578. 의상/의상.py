def solution(clothes):
    answer = 1
    dic = {}
    # print(dic.get("h"))
    for c in clothes:
        if c[1] in dic:
            dic[c[1]] += 1
        else:
            dic[c[1]] = 1
    # for c in clothes:
    #     dic[c[1]] = 0
    # for c in clothes:
    #     dic[c[1]] += 1

    for value in dic.values():
        answer *= value + 1
    return answer - 1








# from collections import Counter
# def solution(clothes):
#     answer = 1
#     print(Counter(clothes))
#     cloth = {}
#     cloth_mount = []
#     for v in clothes:
#         cloth[v[1]] = 0
        
#     for v in clothes:
#         cloth[v[1]] += 1
    
#     for i in cloth.values():
#         answer *= (i + 1)
        
#     answer -= 1
#     return answer