import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] ary = new int[n][3];
        int[][] dp_max = new int[n][3];
        int[][] dp_min = new int[n][3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
            ary[i][2] = Integer.parseInt(st.nextToken());
        }

        dp_min[n - 1][0] = dp_max[n - 1][0] = ary[n - 1][0];
        dp_min[n - 1][1] = dp_max[n - 1][1] = ary[n - 1][1];
        dp_min[n - 1][2] = dp_max[n - 1][2] = ary[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            dp_max[i][0] = ary[i][0] + Math.max(dp_max[i + 1][0], dp_max[i + 1][1]);
            dp_max[i][1] = ary[i][1] + Math.max(dp_max[i + 1][0], Math.max(dp_max[i + 1][1], dp_max[i + 1][2]));
            dp_max[i][2] = ary[i][2] + Math.max(dp_max[i + 1][1], dp_max[i + 1][2]);

            dp_min[i][0] = ary[i][0] + Math.min(dp_min[i + 1][0], dp_min[i + 1][1]);
            dp_min[i][1] = ary[i][1] + Math.min(dp_min[i + 1][0], Math.min(dp_min[i + 1][1], dp_min[i + 1][2]));
            dp_min[i][2] = ary[i][2] + Math.min(dp_min[i + 1][1], dp_min[i + 1][2]);
        }

        System.out.println(Math.max(dp_max[0][0], Math.max(dp_max[0][1], dp_max[0][2])) + " "
                + Math.min(dp_min[0][0], Math.min(dp_min[0][1], dp_min[0][2])));
    }
}