import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        if (n == k) {
            System.out.println(0);
            System.out.println(1);
            return;
        }
        int MAX = 1_000_000;

        int[] dp = new int[MAX + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { n, 0 });
        dp[n] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cnt = cur[1];
            int next = cur[0] + 1;

            if (next <= MAX && dp[next] > cnt + 1) {
                queue.offer(new int[] { next, cnt + 1 });
                dp[next] = cnt + 1;
            }
            next = cur[0] - 1;
            if (next >= 0 && dp[next] > cnt + 1) {
                queue.offer(new int[] { next, cnt + 1 });
                dp[next] = cnt + 1;
            }
            next = cur[0] * 2;
            if (next <= MAX && dp[next] > cnt + 1) {
                queue.offer(new int[] { next, cnt + 1 });
                dp[next] = cnt + 1;
            }
        }
        int ans = dp[k];

        Arrays.fill(dp, Integer.MAX_VALUE);
        queue = new LinkedList<>();
        queue.offer(new int[] { n, 0 });
        dp[n] = 0;
        int ansCnt = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cnt = cur[1];
            int next = cur[0] + 1;

            if (next <= MAX && dp[next] >= cnt + 1) {
                if (dp[next] == cnt + 1) {
                    if (next == k && ans == cnt + 1) {
                        ansCnt++;
                    }
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                } else {
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                }
            }
            next = cur[0] - 1;
            if (next >= 0 && dp[next] >= cnt + 1) {
                if (dp[next] == cnt + 1) {
                    if (next == k && ans == cnt + 1) {
                        ansCnt++;
                    }
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                } else {
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                }
            }
            next = cur[0] * 2;
            if (next <= MAX && dp[next] >= cnt + 1) {
                if (dp[next] == cnt + 1) {
                    if (next == k && ans == cnt + 1) {
                        ansCnt++;
                    }
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                } else {
                    queue.offer(new int[] { next, cnt + 1 });
                    dp[next] = cnt + 1;
                }
            }
        }
        System.out.println(ans);
        System.out.println(ansCnt + 1);
    }
}