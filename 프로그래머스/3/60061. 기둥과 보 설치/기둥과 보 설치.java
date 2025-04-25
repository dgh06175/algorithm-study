class Solution {
    // 구조물은 교차점 좌표를 기준으로 보는 오른쪽, 기둥은 위쪽 방향으로 설치 또는 삭제합니다.
    boolean[][] bo; // bo[i][j] = true 라면 i,j 좌표의 오른쪽에 구조물이 있다는 뜻
    boolean[][] pillar; // pillar[i][j] = true 라면 i,j 칸에 위로 기둥이 있다는 뜻
    int n;
    
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        bo = new boolean[n+1][n+1];
        pillar = new boolean[n+1][n+1];
        for(int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            boolean isBo = build_frame[i][2] == 1;
            boolean isBuild = build_frame[i][3] == 1;
            doJob(x, y, isBo, isBuild);
        }
        
        int count = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if (bo[i][j]) {
                    count++;
                }
                if (pillar[i][j]){
                    count++;
                }
            }
        }
        
        int[][] answer = new int[count][3];
        int cnt = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if (pillar[i][j]){
                    answer[cnt++] = new int[]{i,j,0};
                }
                if (bo[i][j]) {
                    answer[cnt++] = new int[]{i,j,1};
                }
            }
        }
        
        return answer;
    }
    
    void doJob(int x, int y, boolean isBo, boolean isBuild) {
        if (isBo) {
            if (isBuild) { // 보 설치
                if (y == 0) { // 바닥
                    bo[x][y] = true;
                    return;
                }
                if (pillar[x][y-1]) {
                    bo[x][y] = true;
                    return;
                }
                if (pillar[x+1][y-1]) {
                    bo[x][y] = true;
                    return;
                }
                if (x > 0 && x < n && bo[x+1][y] && bo[x-1][y]) {
                    bo[x][y] = true;
                    return;
                }
            } else { // 보 제거
                bo[x][y] = false;
                if (!isValid()) {
                    bo[x][y] = true; // 복구
                }
            }
        } else {
            if (isBuild) { // 기둥 설치
                if (y == 0) { // 바닥
                    pillar[x][y] = true;
                    return;
                }
                if (pillar[x][y - 1]) { // 아래에 기둥 있을때
                    pillar[x][y] = true;
                    return;
                }
                if (bo[x][y]) { // 오른쪽에 보 있을때
                    pillar[x][y] = true;
                    return;
                }
                if (x > 0 && bo[x-1][y]) { // 왼쪽에 보 있을때
                    pillar[x][y] = true;
                    return;
                }
            } else { // 기둥 제거
                pillar[x][y] = false;
                if (!isValid()) {
                    pillar[x][y] = true; // 복구
                }
            }
        }
    }
    
    boolean isValid() {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y]) {
                    if (!(y == 0 || pillar[x][y - 1] || bo[x][y] || (x > 0 && bo[x - 1][y]))) {
                        return false;
                    }
                }
                if (bo[x][y]) {
                    if (!(pillar[x][y - 1] || pillar[x + 1][y - 1] ||
                          (x > 0 && bo[x - 1][y] && bo[x + 1][y]))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
