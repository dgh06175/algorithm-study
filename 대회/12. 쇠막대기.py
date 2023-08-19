data = input()
# 앞에서부터, '('가 나오면 cnt++ ')'가 나오면 cnt--
# 레이저가 나올때마다 ans += cnt

ans , cnt = 0, 0
barcnt = 0
chk = False
for i in range(len(data)-1):
  if chk:
    chk = False
    continue
  if data[i] == '(' and data[i+1] == ')':
    ans += cnt
    #print(i,"에서",cnt,"더함")
    chk = True
  elif data[i] == '(':
    cnt += 1
    barcnt += 1
  else:
    cnt -= 1

print(ans + barcnt)