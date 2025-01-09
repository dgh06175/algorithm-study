max = 0
x = 0
y = 0

arr = []
for _ in range(9):
    arr.append(list(map(int, input().split())))
    


for i, a in enumerate(arr):
    for j, v in enumerate(a):
        if v > max:
            max = v
            x = i
            y = j

print(max)
print(x + 1, y + 1)
