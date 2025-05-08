import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // <= 100
        int M = Integer.parseInt(st.nextToken()); // <= 10,000,000
        int[] m = new int[N + 1];
        int[] c = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(st.nextToken()); // <= 10,000,000
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken()); // <= 100
        }

        // M 바이트를 확보하기 위한 최소 c 비용 구하기
        int mSum = Arrays.stream(c).sum();
        int[][] dp = new int[N + 1][mSum + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= mSum; j++) {
                if (j >= c[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c[i]] + m[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 0; i <= mSum; i++) {
            if (dp[N][i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
