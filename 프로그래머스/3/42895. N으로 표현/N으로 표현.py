def solution(N, number):
    s = [set() for _ in range(9)]
    for num in range(1, 9):
        s[num].add(int(str(N) * num))
        
    for i in range(2, 9):
        for j in range(1, i):
            for n in s[j]:
                for m in s[i-j]:
                    if m != 0:
                        s[i].add(n+m)
                        s[i].add(n-m)
                        s[i].add(n*m)
                        s[i].add(n//m)

    for i in range(len(s)):
        if number in s[i]:
            return i
    return -1

