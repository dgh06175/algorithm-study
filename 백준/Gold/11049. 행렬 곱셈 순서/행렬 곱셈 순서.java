import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] ary = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];
        for (int t = 1; t < n; t++) {
            for (int i = 0; i < n - t; i++) {
                int j = i + t;
                if (t == 1) {
                    dp[i][j] = ary[i][0] * ary[j][0] * ary[j][1];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + ary[i][0] * ary[k][1] * ary[j][1]);
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }
}