n, m = map(int, input().split())
cards = list(map(int, input().split()))

max_sum = 0
for a in range(0, n):
    for b in range(a + 1, n):
        for c in range(b + 1, n):
            card_sum = cards[a] + cards[b] + cards[c]
            if max_sum < card_sum and not card_sum > m:
                max_sum = card_sum

print(max_sum)
