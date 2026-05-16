class Solution {
    private static final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        boolean[][] pud = new boolean[n + 1][m + 1];
        for(int i = 0; i < puddles.length; i++) {
            pud[puddles[i][1]][puddles[i][0]] = true;
        }
        
        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (pud[i][j]) {
                    continue;
                }
                
                int tmp = 0;
                if (!pud[i-1][j]) {
                    tmp += dp[i-1][j];
                }
                
                if (!pud[i][j-1]) {
                    tmp += dp[i][j-1];
                }
                dp[i][j] = tmp % MOD;
            }
        }
        // for(int i = 0; i < n + 1; i++) {
        //     for(int j = 0; j < m + 1; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }   
        //     System.out.println();
        // }
        
        return dp[n][m];
    }
}

// 0 0 0 0 0
// 0 1 1 1 1
// 0 1 X 1 2
// 0 1 1 2 4