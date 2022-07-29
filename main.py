# 4 * 1 =  4   3 * 1 =  3
# 4 * 2 =  8   3 * 2 =  6
# 4 * 3 = 12   3 * 3 =  9
# 4 * 4 = 16   3 * 4 = 12
# 4 * 5 = 20   3 * 5 = 15
# 4 * 6 = 24   3 * 6 = 18
# 4 * 7 = 28   3 * 7 = 21
# 4 * 8 = 32   3 * 8 = 24
# 4 * 9 = 36   3 * 9 = 27

while(True):
  start, end = map(int,input().split())
  if start < 2 or start > 9 or end < 2 or end > 9:
    print("INPUT ERROR!")
    continue
  updown = 1
  if start > end:
    updown = -1
  for i in range(1, 10):
    for j in range(start, end + updown, updown):
      print(f"{j} * {i} = %2s" % str(i*j), end='')
      if j is not end:
        print("   ", end='')
    print()
  break

