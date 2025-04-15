import java.util.*;

class Main {
    final static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    final static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    final static int N = 4;

    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fish[][] map = new Fish[N][N];
        Fish[] fishs = new Fish[N * N + 1];
        Fish shark;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = sc.nextInt();
                int direction = sc.nextInt() - 1;
                Fish fish = new Fish(num, i, j, direction, false);
                fishs[num] = fish;
                map[i][j] = fish;
            }
        }

        // 1. 상어 입장
        shark = new Fish(-1, 0, 0, map[0][0].direction, true);
        fishs[map[0][0].number] = null;
        int eat = map[0][0].number;
        map[0][0] = shark;

        // 상어가 나갈때 까지 무한 반복
        attempt(map, fishs, shark, eat);
        // 2. 물고기 이동
        // 3. 상어 이동 (백트래킹)

        System.out.println(answer);
        sc.close();
    }

    static void attempt(Fish[][] map, Fish[] fishs, Fish shark, int eat) {
        // 1. 물고기 이동, 이동 후 map, fishs 배열은 따로 생성
        Fish[][] dupMap = deepCopyMap(map);
        Fish[] dupFishs = deepCopyFishs(dupMap);
        for (int i = 1; i < N * N + 1; i++) {
            if (dupFishs[i] == null)
                continue;
            Fish fish = dupFishs[i];
            for (int d = 0; d < 8; d++) { // 방향 순
                int new_d = (fish.direction + d) % 8;
                int ny = fish.y + dy[new_d];
                int nx = fish.x + dx[new_d];
                if (isValidPos(ny, nx)) {
                    if (dupMap[ny][nx] == null) {
                        dupMap[fish.y][fish.x] = null;
                        dupMap[ny][nx] = fish;
                        fish.y = ny;
                        fish.x = nx;
                        fish.direction = new_d;
                        break;
                    }
                    if (dupMap[ny][nx].isShark) {
                        continue;
                    } else {
                        fish.direction = new_d;
                        swap(dupMap, fish, dupMap[ny][nx]);
                        break;
                    }
                }
            }
        }

        // 2. 상어 이동
        // 가능한 경우 모두 시도
        // 각 시도 마다 이동 후 배열 따로 생성, attempt 호출
        // 갈 수 있는 곳 없을 경우 answer 와 eat 비교 후 바로 반환
        boolean canMove = false;
        for (int i = 1; i < 4; i++) {
            int ny = shark.y + dy[shark.direction] * i;
            int nx = shark.x + dx[shark.direction] * i;
            if (!isValidPos(ny, nx)) {
                break; // break 해도 될듯?
            }

            if (dupMap[ny][nx] == null) {
                continue;
            }

            // 먹기
            // 0. canMove
            canMove = true;
            // 1. 물고기의 위치와 방향을 가진 상어 생성
            Fish newShark = new Fish(-1, ny, nx, dupMap[ny][nx].direction, true);
            // 2. 상어의 원래 위치 null, 물고기 위치에 상어 넣은 배열 새로 생성
            Fish[][] dupdupMap = deepCopyMap(dupMap);
            dupdupMap[shark.y][shark.x] = null;
            dupdupMap[ny][nx] = newShark;
            // 3. 새로운 dupFish 배열 생성
            Fish[] dupdupFishs = deepCopyFishs(dupdupMap);
            // 함수 호출 (eat 증가한)
            attempt(dupdupMap, dupdupFishs, newShark, eat + dupMap[ny][nx].number);
        }
        if (!canMove) {
            answer = Math.max(answer, eat);
        }
    }

    static void swap(Fish[][] map, Fish fish1, Fish fish2) {
        int y1 = fish1.y, x1 = fish1.x;
        int y2 = fish2.y, x2 = fish2.x;

        fish1.y = y2;
        fish1.x = x2;
        fish2.y = y1;
        fish2.x = x1;

        map[y2][x2] = fish1;
        map[y1][x1] = fish2;
    }

    static boolean isValidPos(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    static Fish[][] deepCopyMap(Fish[][] map) {
        Fish[][] dupMap = new Fish[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) {
                    dupMap[i][j] = null;
                } else {
                    dupMap[i][j] = new Fish(map[i][j]);
                }
            }
        }
        return dupMap;
    }

    static Fish[] deepCopyFishs(Fish[][] map) {
        Fish[] dupFishs = new Fish[N * N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null || map[i][j].number == -1) {
                    continue;
                }
                dupFishs[map[i][j].number] = map[i][j];
            }
        }
        return dupFishs;
    }
}

class Fish {
    int number;
    int y, x;
    int direction;
    boolean isShark;

    Fish(int number, int y, int x, int direction, boolean isShark) {
        this.number = number;
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.isShark = isShark;
    }

    Fish(Fish other) {
        this.number = other.number;
        this.y = other.y;
        this.x = other.x;
        this.direction = other.direction;
        this.isShark = other.isShark;
    }
}