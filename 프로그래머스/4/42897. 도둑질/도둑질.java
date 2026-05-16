class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[] dp = new int[n];
        return Math.max(rob1(money, n, dp), rob2(money, n, dp));
    }
    
    int rob1(int[] money, int n, int[] dp) { // 첫번쨰 방 털기
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        
        for(int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
            // dp[i][0] = dp[i-1][1] + money[i];
            // dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
        }
        
        return dp[n - 2];
    }
    
    int rob2(int[] money, int n, int[] dp) { // 첫번쨰 방 안털기
        dp[0] = 0;
        dp[1] = money[1];
        
        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        
        return dp[n - 1];
    }
}