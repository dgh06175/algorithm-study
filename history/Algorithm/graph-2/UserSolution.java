import java.util.*;

class UserSolution {
    int[][] map;

    public void init(int N, int mMap[][]) {
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = mMap[i][j];
            }
        }
    }

    public int numberOfCandidate(int M, int mStructure[]) {
        List<int[][]> candidates = makeAllCandidates(M, mStructure);
        return candidates.size();
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        List<int[][]> maps = makeAllCandidates(M, mStructure); // O(N^2)
        if (maps.size() == 0) {
            return -1;
        }

        int answer = 0;
        for (int[][] map : maps) {
            int islandCount = calcIslandCount(map, mSeaLevel);
            answer = Math.max(answer, islandCount);
        }
        return answer;
    }

    private List<int[][]> makeAllCandidates(int M, int mStructure[]) {
        List<int[][]> candidates = new ArrayList<int[][]>();
        int N = map.length;
        boolean[][] visitedY = new boolean[N][N];
        boolean[][] visitedX = new boolean[N][N];

        // 원래 모양
        candidatesX(M, mStructure, candidates, N, visitedX);
        if (M == 1) {
            return candidates;
        }

        // 90도 회전 (세로)
        candidatesY(M, mStructure, candidates, N, visitedY);

        int[] structure = new int[M];
        for (int i = 0; i < M; i++) {
            structure[i] = mStructure[M - i - 1];
        }

        // 180도 회전
        candidatesX(M, structure, candidates, N, visitedX);

        // 270도 회전
        candidatesY(M, structure, candidates, N, visitedY);
        return candidates;
    }

    private void candidatesX(int M, int[] mStructure, List<int[][]> candidates, int N, boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                boolean valid = true;
                int height = map[i][j] + mStructure[0];
                for (int l = 1; l < M; l++) {
                    if (height != map[i][j + l] + mStructure[l]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    if (visited[i][j])
                        continue;

                    int[][] candidate = new int[N][N];
                    for (int y = 0; y < N; y++) {
                        candidate[y] = Arrays.copyOf(map[y], N);
                    }
                    for (int m = 0; m < M; m++) {
                        candidate[i][j + m] += mStructure[m];
                    }
                    candidates.add(candidate);
                    visited[i][j] = true;
                }
            }
        }
    }

    private void candidatesY(int M, int[] structure, List<int[][]> candidates, int N, boolean[][] visited) {
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j < N; j++) {
                boolean valid = true;
                int height = map[i][j] + structure[0];
                for (int l = 1; l < M; l++) {
                    if (height != map[i + l][j] + structure[l]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    if (visited[i][j])
                        continue;

                    int[][] candidate = new int[N][N];
                    for (int y = 0; y < N; y++) {
                        candidate[y] = Arrays.copyOf(map[y], N);
                    }
                    for (int m = 0; m < M; m++) {
                        candidate[i + m][j] += structure[m];
                    }
                    candidates.add(candidate);
                    visited[i][j] = true;
                }
            }
        }
    }

    private int calcIslandCount(int[][] wallMap, int seaLevel) {
        class Point {
            int y;
            int x;

            Point(int y, int x) {
                this.y = y;
                this.x = x;
            }
        }
        int N = wallMap.length;
        int count = N * N;
        boolean visited[][] = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        int[] dy = { 0, 0, 1, -1 };
        int[] dx = { 1, -1, 0, 0 };

        // 테두리 조건맞으면 바다로 바꾸기
        for (int i = 0; i < N; i++) {
            if (wallMap[0][i] < seaLevel) {
                queue.offer(new Point(0, i));
                visited[0][i] = true;
            }
            if (wallMap[N - 1][i] < seaLevel) {
                queue.offer(new Point(N - 1, i));
                visited[N - 1][i] = true;
            }
            if (wallMap[i][0] < seaLevel) {
                queue.offer(new Point(i, 0));
                visited[i][0] = true;
            }
            if (wallMap[i][N - 1] < seaLevel) {
                queue.offer(new Point(i, N - 1));
                visited[i][N - 1] = true;
            }
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int y = point.y;
            int x = point.x;

            if (wallMap[y][x] != 0) {
                wallMap[y][x] = 0;
                count--;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    if (!visited[ny][nx] && wallMap[ny][nx] < seaLevel) {
                        queue.offer(new Point(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return count;
    }
}