def solution(brown, yellow):
    for width in range(3, (brown - 2) // 2 + 1):
        height = brown / 2 - width + 2
        if yellow == width * height - brown:
            return [max(width, height), min(width, height)]

# 카펫의 가로 width 세로 height
# brown = width * 2 + height * 2 - 4
# yellow = width * height - brown

# brown 이랑 yellow 가 주어진다. width와 height를 구해야한다.
# width를 3부터 (brown - 2) // 2 돌리며 완전탐색을 한다,
