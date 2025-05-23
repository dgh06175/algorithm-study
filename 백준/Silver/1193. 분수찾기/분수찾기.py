n = int(input())

x = 0
y = 0

line = 0
end = 0
while n > end:
    line += 1
    end += line

gap = end - n
if line % 2 == 0:
    x = line - gap
    y = gap + 1
else:
    x = gap + 1
    y = line - gap

print(f"{x}/{y}")
