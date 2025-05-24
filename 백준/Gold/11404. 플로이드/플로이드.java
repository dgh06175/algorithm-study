import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int MAX_VALUE = 100_000 * n * n + 1;

        int[][] ary = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ary[i], MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            ary[i][i] = 0;
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            ary[a][b] = Math.min(ary[a][b], c);
        }

        for (int x = 0; x < n; x++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ary[i][j] = Math.min(ary[i][j], ary[i][x] + ary[x][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ary[i][j] == MAX_VALUE) {
                    System.out.print("0 ");
                } else {
                    System.out.print(ary[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}