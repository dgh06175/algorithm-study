import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ary = new int[n];
        // dp[s][i] = 연속 s번째로 밟고 있고, i번째 계단을 밟았을때 최대 점수
        int[][] dp = new int[3][n];

        for (int i = 0; i < n; i++) {
            ary[i] = sc.nextInt();
        }

        if (n == 2) {
            System.out.println(ary[0] + ary[1]);
            return;
        }

        if (n == 1) {
            System.out.println(ary[0]);
            return;
        }

        dp[1][0] = ary[0];
        dp[1][1] = ary[1];
        dp[2][1] = ary[0] + ary[1];

        for (int i = 2; i < n; i++) {
            // 두칸 뛴 경우. i-2
            dp[1][i] = Math.max(dp[1][i - 2], dp[2][i - 2]) + ary[i];

            // 한칸 뛴 경우. i-1
            dp[2][i] = dp[1][i - 1] + ary[i];
        }

        System.out.println(Math.max(dp[1][n - 1], dp[2][n - 1]));
    }
}

// ary 10 20 15 25 20

// dp0 0 0 0 0 0
// dp1 10 20 25 0 0
// dp2 0 30 35 50 0
