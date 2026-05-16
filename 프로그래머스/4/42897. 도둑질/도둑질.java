class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[][] dp = new int[n][2];
        return Math.max(rob1(money, n, dp), rob2(money, n, dp));
    }
    
    int rob1(int[] money, int n, int[][] dp) { // 첫번쨰 방 털기
        dp[0][0] = money[0];
        dp[0][1] = 0;
        
        for(int i = 1; i < n - 1; i++) {
            dp[i][0] = dp[i-1][1] + money[i];
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
        }
        
        return Math.max(dp[n-2][0], dp[n-2][1]);
    }
    
    int rob2(int[] money, int n, int[][] dp) { // 첫번쨰 방 안털기
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][1] + money[i];
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
        }
        
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}