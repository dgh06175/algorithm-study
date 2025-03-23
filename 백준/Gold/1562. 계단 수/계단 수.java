import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[][][] dp = new long[n + 1][10][1024];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long result = 0;
        for (int i = 1; i <= 9; i++) {
            result = (result + func(dp, n, i, 1 << i)) % 1_000_000_000;
        }

        System.out.println(result);
    }

    static long func(long[][][] dp, int n, int i, int visit) {
        if (n == 1) {
            return visit == 1023 ? 1 : 0;
        }
        if (dp[n][i][visit] != -1) {
            return dp[n][i][visit];
        }

        long result = 0;

        if (i > 0) {
            result += func(dp, n - 1, i - 1, visit | (1 << (i - 1)));
        }
        if (i < 9) {
            result += func(dp, n - 1, i + 1, visit | (1 << (i + 1)));
        }

        return dp[n][i][visit | 1 << i] = result % 1_000_000_000;
    }
}
