import pygame
import random

# 게임화면 설정
pygame.init()
WIDTH, HEIGHT = 600, 600
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("지렁이 게임")

# 색깔 설정
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)

# 게임 설정
FPS = 15
BLOCK_SIZE = 10


# 게임 시작 함수
def game():
    # 초기 지렁이 설정
    x, y = WIDTH / 2, HEIGHT / 2
    velocity_x, velocity_y = BLOCK_SIZE, 0
    snake = [(x, y)]
    snake_length = 30

    # 초기 음식 설정
    food_x, food_y = random.randrange(0, WIDTH - BLOCK_SIZE, BLOCK_SIZE), random.randrange(0, HEIGHT - BLOCK_SIZE,
                                                                                           BLOCK_SIZE)

    # 게임 루프
    while True:
        # 이벤트 처리
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP and velocity_y != BLOCK_SIZE:
                    velocity_x, velocity_y = 0, -BLOCK_SIZE
                elif event.key == pygame.K_DOWN and velocity_y != -BLOCK_SIZE:
                    velocity_x, velocity_y = 0, BLOCK_SIZE
                elif event.key == pygame.K_LEFT and velocity_x != BLOCK_SIZE:
                    velocity_x, velocity_y = -BLOCK_SIZE, 0
                elif event.key == pygame.K_RIGHT and velocity_x != -BLOCK_SIZE:
                    velocity_x, velocity_y = BLOCK_SIZE, 0

        # 지렁이 위치 업데이트
        x += velocity_x
        y += velocity_y

        # 화면 벗어나는지 체크
        if x < 0 or x > WIDTH - BLOCK_SIZE or y < 0 or y > HEIGHT - BLOCK_SIZE:
            game_over()

        # 지렁이 길이 업데이트
        snake.append((x, y))
        if len(snake) > snake_length:
            del snake[0]

        # 음식과 충돌 체크
        if x == food_x and y == food_y:
            snake_length += 1
            food_x, food_y = random.randrange(0, WIDTH - BLOCK_SIZE, BLOCK_SIZE), random.randrange(0,
                                                                                                   HEIGHT - BLOCK_SIZE,
                                                                                                   BLOCK_SIZE)

        # 화면 그리기
        WIN.fill(WHITE)
        pygame.draw.rect(WIN, RED, [food_x, food_y, BLOCK_SIZE, BLOCK_SIZE])
        for block in snake:
            pygame.draw.rect(WIN, BLACK, [block[0], block[1], BLOCK_SIZE, BLOCK_SIZE])
        pygame.display.update()

        # FPS 설정
        clock = pygame.time.Clock()
        clock.tick(FPS)


# 게임 종료 함수
def game_over():
    pygame.quit()
    quit()


# 게임 실행
game()
