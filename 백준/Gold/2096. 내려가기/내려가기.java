import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] ary = new int[n][3];
        int[] prev_max = new int[3];
        int[] prev_min = new int[3];
        int[] now_max = new int[3];
        int[] now_min = new int[3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
            ary[i][2] = Integer.parseInt(st.nextToken());
        }

        prev_min[0] = prev_max[0] = ary[n - 1][0];
        prev_min[1] = prev_max[1] = ary[n - 1][1];
        prev_min[2] = prev_max[2] = ary[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            now_max[0] = ary[i][0] + Math.max(prev_max[0], prev_max[1]);
            now_max[1] = ary[i][1] + Math.max(Math.max(prev_max[0], prev_max[1]), prev_max[2]);
            now_max[2] = ary[i][2] + Math.max(prev_max[1], prev_max[2]);

            now_min[0] = ary[i][0] + Math.min(prev_min[0], prev_min[1]);
            now_min[1] = ary[i][1] + Math.min(Math.min(prev_min[0], prev_min[1]), prev_min[2]);
            now_min[2] = ary[i][2] + Math.min(prev_min[1], prev_min[2]);

            for (int j = 0; j < 3; j++) {
                prev_max[j] = now_max[j];
                prev_min[j] = now_min[j];
            }
        }

        System.out.println(Math.max(prev_max[0], Math.max(prev_max[1], prev_max[2])) + " "
                + Math.min(prev_min[0], Math.min(prev_min[1], prev_min[2])));
    }
}