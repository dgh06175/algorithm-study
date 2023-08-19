import time
ary = []
for v in range(10000):
  ary.append(v)
target = int(input())
  
start_time = time.time() # 측정시작


start = 0
end = len(ary) - 1
mid = (start + end) // 2
result = None
while True:
  mid = (start + end) // 2
  if start > end:
    break
  if ary[mid] == target:
    result = mid
    break
  elif ary[mid] > target:
    end = mid - 1
  else:
    start = mid + 1

print(result + 1)


end_time = time.time() # 측정 종료
start_time2 = time.time() # 측정시작

result2 = None
for i in range(len(ary)):
  if ary[i] == target:
    result2 = i

    
print(result+1)
end_time2 = time.time() # 측정 종료


print("수행시간의 차이는", (end_time2 - start_time2) / (end_time - start_time), "배 입니다.")