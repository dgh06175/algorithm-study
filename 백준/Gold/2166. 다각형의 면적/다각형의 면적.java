import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[][] ary = new long[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            ary[i][0] = Long.parseLong(st.nextToken());
            ary[i][1] = Long.parseLong(st.nextToken());
        }

        double ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                ans += (ary[i][0] * ary[0][1] - ary[0][0] * ary[i][1]);
                continue;
            }
            ans += (ary[i][0] * ary[i + 1][1] - ary[i + 1][0] * ary[i][1]);
        }
        ans = ans > 0.0 ? ans : -1.0 * ans;
        ans /= 2.0;

        ans *= 10.0;
        ans = Math.round(ans);
        ans /= 10.0;
        System.out.printf("%.1f\n", ans);
    }
}
