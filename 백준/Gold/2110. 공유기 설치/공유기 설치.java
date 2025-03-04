import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }
        Arrays.sort(x);

        int start = 1;
        int end = 1_000_000_000;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            boolean result = isValid(n, c, x, mid);
            if (result) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(int n, int c, int[] x, int dist) {
        int count = 1;
        int recent = x[0];
        for (int i = 1; i < n; i++) {
            if (x[i] - recent >= dist) {
                count++;
                recent = x[i];
            }
        }
        return count >= c;
    }
}
