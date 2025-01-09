nums = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

def selection_sort(ary):
  for i in range(len(ary)):
    min_index = i
    for j in range(i + 1, len(ary)):
      if ary[min_index] > ary[j]:
        min_index = j
    ary[i], ary[min_index] = ary[min_index], ary[i]

def bubble_sort(ary):
  for _ in range(len(ary) - 1):
    for i in range(len(ary) - 1):
      if ary[i] > ary[i + 1]:
        ary[i], ary[i + 1] = ary[i + 1], ary[i]
      
      


#selection_sort(nums)
bubble_sort(nums)
print(nums)