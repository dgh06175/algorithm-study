student = []
for _ in range(28):
    n = int(input())
    student.append(n)

bad_student = []

for i in range(1, 31):
    if i not in student:
        bad_student.append(i)

bad_student.sort()
print(bad_student[0])
print(bad_student[1])
