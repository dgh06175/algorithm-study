class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int num = 0;
                if (i >= j) {
                    num = triangle[i][j];
                }
                
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + num;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + num;
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[n - 1][i]);
        }
        return answer;
    }
}
// dp[i][j] = i,j 까지 왔을때 거쳐간 숫자의 최댓값
// dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]

// 7
// 3 8
// 8 1 0
// 2 7 4 4
// 4 5 2 6 5