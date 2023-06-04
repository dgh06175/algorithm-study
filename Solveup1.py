n = int(input())
num = 1
for i in range(n):
	a, b = map(int, input().split())
	if a == 1:
		if b == 2:
			print(f"#{i+1} B")
		elif b == 3:
			print(f"#{i+1} A")

	elif a == 2:
		if b == 1:
			print(f"#{i+1} A")

		elif b == 3:
			print(f"#{i+1} B")

	elif a == 3:
		if b == 1:
			print(f"#{i+1} B")

		elif b == 2:
			print(f"#{i+1} A")
