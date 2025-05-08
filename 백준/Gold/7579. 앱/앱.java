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
        int[] m = new int[N];
        int[] c = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken()); // <= 10,000,000
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken()); // <= 100
        }

        int cSum = Arrays.stream(c).sum();
        long[] dp = new long[cSum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = cSum; j >= c[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - c[i]] + m[i]);
            }
        }

        for (int i = 0; i <= cSum; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
