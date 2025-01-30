import pygame
import random

# 화면 설정
pygame.init()
screen_width = 600
screen_height = 400
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Bouncing Ball Game")
# 화면 크기
WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600

# 맵 크기
MAP_WIDTH = 1600
MAP_HEIGHT = 1200

# 공 크기
BALL_SIZE = 50

# 공 위치 초기값
ball_position = [MAP_WIDTH//2 - BALL_SIZE//2, MAP_HEIGHT//2 - BALL_SIZE//2]

# 공 이동 방향 초기값
ball_direction = "right"

# 공 이동 속도
BALL_SPEED = 5

# 카메라 위치 초기값
offset_x = 0
offset_y = 0

# 색상 설정
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# 공 객체
class Ball:
    def __init__(self, x, y, radius, color):
        self.x = x
        self.y = y
        self.radius = radius
        self.color = color
        self.speed_x = random.randint(3, 6) * random.choice([-1, 1])
        self.speed_y = random.randint(3, 6) * random.choice([-1, 1])

    def draw(self):
        pygame.draw.circle(screen, self.color, (self.x, self.y), self.radius)

    def move(self):
        self.x += self.speed_x
        self.y += self.speed_y

    def bounce(self):
        if self.x > screen_width - self.radius or self.x < self.radius:
            self.speed_x *= -1
        if self.y > screen_height - self.radius or self.y < self.radius:
            self.speed_y *= -1


def moving(ball_position, ball_direction, speed):
    # 현재 방향에 따라 공 위치 업데이트
    if ball_direction == "right":
        ball_position[0] += speed
    elif ball_direction == "left":
        ball_position[0] -= speed
    elif ball_direction == "up":
        ball_position[1] -= speed
    elif ball_direction == "down":
        ball_position[1] += speed

    # 공이 벽에 부딪혔는지 체크하여 방향 업데이트
    if ball_position[0] <= 0:
        ball_direction = "right"
    elif ball_position[0] + BALL_SIZE >= MAP_WIDTH:
        ball_direction = "left"
    elif ball_position[1] <= 0:
        ball_direction = "down"
    elif ball_position[1] + BALL_SIZE >= MAP_HEIGHT:
        ball_direction = "up"

    return ball_position, ball_direction


# 게임 루프
running = True
ball = Ball(screen_width // 2, screen_height // 2, 20, GREEN)
while running:
    # 이벤트 처리
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEBUTTONDOWN:
            # 마우스 클릭 시 공 이동 시작
            if ball.x - ball.radius < event.pos[0] < ball.x + ball.radius and ball.y - ball.radius < event.pos[1] < ball.y + ball.radius:
                moving = True
                offset_x = ball.x - event.pos[0]
                offset_y = ball.y - event.pos[1]
        elif event.type == pygame.MOUSEBUTTONUP:
            # 마우스 버튼 뗄 시 공 이동 종료
            moving = False
        elif event.type == pygame.MOUSEMOTION:
            # 마우스 드래그 시 공 이동
            if moving:
                ball.x = event.pos[0] + offset_x
                ball.y = event.pos[1] + offset_y

    # 게임 로직
    ball.move()
    ball.bounce()

    # 화면 업데이트
    screen.fill(BLACK)
    ball.draw()
    pygame.display.flip()

# 게임 종료
pygame.quit()
