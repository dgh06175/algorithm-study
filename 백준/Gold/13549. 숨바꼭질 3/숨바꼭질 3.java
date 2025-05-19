import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int MAX = 200_000;

        int[] dp = new int[MAX];
        Arrays.fill(dp, Integer.MAX_VALUE);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        dp[n] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : new int[] { cur + 1, cur - 1 }) {
                if (next < 0 || next >= MAX) {
                    continue;
                }
                if (dp[next] > dp[cur] + 1) {
                    dp[next] = dp[cur] + 1;
                    queue.offer(next);
                }
            }
            int next = cur * 2;
            if (next < 0 || next >= MAX) {
                continue;
            }
            if (dp[next] > dp[cur]) {
                dp[next] = dp[cur];
                queue.offer(next);
            }
        }
        System.out.println(dp[k]);
        sc.close();
    }
}