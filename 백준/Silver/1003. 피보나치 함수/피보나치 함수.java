import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[][] dp = new int[41][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }
    }
}

// d[0][0] = 1, d[0][1] = 0
// d[1][0] = 0, d[1][1] = 1
// d[2][0] = 1, d[2][1] = 1
// d[3][0] = 1, d[3][1] = 2
// d[4][0] = 2, d[4][1] = 3

// d[n][0] = d[n-1][0] + d[n-2][0]
// d[n][1] = d[n-1][1] + d[n-2][1]