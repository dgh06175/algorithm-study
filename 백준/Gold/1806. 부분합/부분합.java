import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, s;
    static int[] ary;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);
        StringTokenizer st = new StringTokenizer(bf.readLine());
        ary = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = ary[0];
        int ans = Integer.MAX_VALUE;
        while (start <= end && end < n) {
            if (sum >= s) {
                ans = Math.min(end - start + 1, ans);
                sum -= ary[start++];
            } else {
                sum += ary[++end];
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(ans);
    }
}
