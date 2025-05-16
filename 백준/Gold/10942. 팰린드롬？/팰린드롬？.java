import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ary = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken()); // ~ 100,000
        }
        boolean[][] dp = new boolean[n][n];
        // dp[i][j] = i원소부터 j원소까지 수가 팰린드롬이다.

        // 크기 1이면 무조건 true
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 크기 2면 둘이 같으면 true
        for (int i = 0; i < n - 1; i++) {
            if (ary[i] == ary[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 길이 3인 펠린드롬부터 길이 n인것 까지
        for (int k = 2; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                int j = i + k;
                // 양끝이 같고 그 안쪽이 펠린드롬이면 펠린드롬
                if ((ary[i] == ary[j]) && (dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[a][b] ? 1 : 0);
            if (i != m - 1) {
                sb.append('\n');
            }
        }
        System.out.println(sb.toString());
    }
}