import itertools

def solution(word):
    arr = ['A', 'B', 'C']
    nPir = itertools.product(arr, repeat = 2)
    print(list(nPir))
#     alpha = ['A', 'E', 'I', 'O', 'U']
#     dictionary = []
#     for r in range(1, len(alpha) + 1):
#         for i in itertools.product(alpha, repeat = r):
#             dictionary.append("".join(i))
    
#     return sorted(dictionary).index(word) + 1