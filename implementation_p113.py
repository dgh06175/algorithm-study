n = int(input())

count = 0

# hour = 0
# min =  0
# sec = 0

# while hour <= n:
#   sec += 1
#   if sec == 60:
#     sec = 0
#     min += 1
#   if min == 60:
#     min = 0
#     hour += 1
#   if '3' in str(hour):
#     count += 1
#     continue
#   if '3' in str(min):
#     count += 1
#     continue
#   if '3' in str(sec):
#     count += 1
#     continue
    
# print(count)

for h in range(n+1):
  for m in range(60):
    for s in range(60):
      if '3' in str(h) + str(m) + str(s):
        count+=1

print(count)