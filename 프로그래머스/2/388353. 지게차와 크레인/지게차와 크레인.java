class Solution {
    final static int[] dy = {1, -1, 0, 0};
    final static int[] dx = {0, 0, 1, -1};
    
    int n, m;
    char[][] ary;
    
    public int solution(String[] storage, String[] requests) {
        int deletedCount = 0;
        n = storage.length;
        m = storage[0].length();
        ary = new char[n][m];
        for(int i = 0; i < n; i++) {
            ary[i] = storage[i].toCharArray();
        }
        for(String request: requests) {
            char target = request.charAt(0);
            if (request.length() == 1) {
                int count = forkLift(target);
                // System.out.println(count);
                deletedCount += count;
            } else {
                int count = crane(target);
                // System.out.println(count);
                deletedCount += count;
            }
        }
        
        return n * m - deletedCount;
    }
    
    int forkLift(char target) {
        int count = 0;
        boolean[][] isDeleted = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (ary[i][j] == target) {
                    boolean[][] visited = new boolean[n][m];
                    if (canGoOut(visited, i, j)) {
                        isDeleted[i][j] = true;
                        count += 1;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (isDeleted[i][j]) {
                    ary[i][j] = 0;
                }
            }
        }
        return count;
    }
    
    boolean canGoOut(boolean[][] visited, int y, int x) {
        visited[y][x] = true;
        
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                return true;
            }
            if (ary[ny][nx] == 0 && !visited[ny][nx]) {
                if (canGoOut(visited, ny, nx)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    int crane(char target) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (ary[i][j] == target) {
                    ary[i][j] = 0;
                    count += 1;
                }
            }
        }
        return count;
    }   
}

// 전체 ary 돌면서 (N^2)
// A면
// A부터 DFS 돌려서 외곽으로 나갈 수 있으면 (5V)
// A 제거
// -> N^4 -> 6백만