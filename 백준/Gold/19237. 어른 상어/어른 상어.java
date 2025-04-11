import java.io.*;
import java.util.*;

public class Main {
    // 0 위, 1아래, 2왼쪽, 3오른쪽
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };
    static int n, m, k;
    static Shark[] sharks;
    static Smell[][] smellMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharks = new Shark[m + 1];
        smellMap = new Smell[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                smellMap[i][j] = new Smell(-1, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value != 0) {
                    Shark shark = new Shark(i, j, value);
                    sharks[value] = shark;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            sharks[i].setDirection(Integer.parseInt(st.nextToken()) - 1);
        }

        for (int i = 1; i <= m; i++) {
            int[][] priority = new int[4][4];
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int[] pri = new int[4];
                for (int p = 0; p < 4; p++) {
                    pri[p] = Integer.parseInt(st.nextToken()) - 1;
                }
                priority[j] = pri;
            }
            sharks[i].setPriority(priority);
        }

        addSmell();

        int time = 0;
        while (true) {
            if (time > 1000) {
                System.out.println(-1);
                return;
            }
            // 1번 상어만 격자에 남았는지 체크
            if (checkSharkOnlyOne()) {
                System.out.println(time);
                return;
            }

            // 모든 상어 이동
            moveSharks();

            // 모든 칸 냄새 감소하기
            reDuceSmell();

            // 냄새 퍼트리기
            addSmell();

            time++;
        }
    }

    static boolean checkSharkOnlyOne() {
        int sharkCount = 0;
        boolean oneSharkExist = false;
        for (int i = 1; i <= m; i++) {
            Shark s = sharks[i];
            if (s == null)
                continue;
            if (s.num == 1)
                oneSharkExist = true;
            sharkCount += 1;
        }
        return sharkCount == 1 && oneSharkExist;
    }

    static void addSmell() {
        for (int i = 1; i <= m; i++) {
            Shark s = sharks[i];
            if (s == null)
                continue;
            int y = s.y;
            int x = s.x;

            smellMap[y][x] = new Smell(s.num, k);
        }
    }

    static void reDuceSmell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (smellMap[i][j].count > 0) {
                    smellMap[i][j].count--;
                }
            }
        }
    }

    // 냄새 배열 체크하며 우선순위 방향으로 모든 상어 이등
    static void moveSharks() {
        int[][] newBoard = new int[n][n];

        for (int i = 1; i <= m; i++) {
            if (sharks[i] == null)
                continue;

            Shark s = sharks[i];
            int cy = s.y;
            int cx = s.x;
            boolean moved = false;
            int newY = -1, newX = -1, newDir = -1;

            int[][] prio = s.priority;
            for (int d : prio[s.direction]) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n)
                    continue;
                if (smellMap[ny][nx].count == 0) {
                    newY = ny;
                    newX = nx;
                    newDir = d;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                for (int d : prio[s.direction]) {
                    int ny = cy + dy[d];
                    int nx = cx + dx[d];
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n)
                        continue;
                    if (smellMap[ny][nx].sharkNum == s.num) {
                        newY = ny;
                        newX = nx;
                        newDir = d;
                        break;
                    }
                }
            }

            if (newBoard[newY][newX] == 0) {
                newBoard[newY][newX] = s.num;
                s.updatePosition(newY, newX, newDir);
            } else {
                if (s.num < newBoard[newY][newX]) {
                    int eliminated = newBoard[newY][newX];
                    sharks[eliminated] = null;
                    newBoard[newY][newX] = s.num;
                    s.updatePosition(newY, newX, newDir);
                } else {
                    sharks[s.num] = null;
                }
            }
        }
    }
}

class Smell {
    int sharkNum, count;

    Smell(int sharkNum, int count) {
        this.sharkNum = sharkNum;
        this.count = count;
    }
}

class Shark {
    int y, x, num;
    int direction;
    int[][] priority; // i방향일때의 방향 순서

    Shark(int y, int x, int num) {
        this.y = y;
        this.x = x;
        this.num = num;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setPriority(int[][] priority) {
        this.priority = priority;
    }

    public void updatePosition(int newY, int newX, int newDir) {
        this.y = newY;
        this.x = newX;
        this.direction = newDir;
    }
}