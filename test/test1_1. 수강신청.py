n = int(input())
a = list(map(int,input().split()))
b, c = map(int, input().split())
# n = 개설된 과목의 수
# a = 과목별 수강신청 인원 수
# b = 교수님의 담당 학생 수
# c = 조교의 담당 학생수

answer = 0
for student_count in a:
  remain = student_count - b
  answer += 1
  if remain > 0:
    answer += remain // c
    if remain % c > 0:
      answer += 1

print(answer)