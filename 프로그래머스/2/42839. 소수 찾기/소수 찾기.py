import math
from itertools import permutations

def is소수(a):
    if a <= 1:
        return False
    for i in range(2, int(math.sqrt(a) + 1)):
        if a % i == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    arr = []
    
    for v in numbers:
        arr.append(int(v))
    
    perm = []
    for i in range(1, len(numbers) + 1):
        perm.append(permutations(numbers, i))
        
    s = []    
        
    for i in perm:
        for j in i:
            s.append(int("".join(j)))
            
    s = set(s)
    print(s)
    
    for sex in s:
        if is소수(sex):
            answer += 1
            
    return answer