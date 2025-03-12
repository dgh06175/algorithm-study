import java.util.*;
import java.io.*;

public class Main {
    private static final long MOD = 100_000_000_000_031L;
    private static final int X = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int start = 1;
        int end = l - 1;

        int ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isValid(str, mid)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(String str, int x) {
        Set<Long> set = new HashSet<>();
        long hash = 0;
        long X_power = 1;

        for (int i = 0; i < x; i++) {
            hash = (hash * X + str.charAt(i)) % MOD;
            if (i > 0) {
                X_power = (X_power * X) % MOD;
            }
        }
        // System.out.println(str.substring(0, 2) + "의 해시: " + hash);
        set.add(hash);

        for (int i = x; i < str.length(); i++) {
            hash = (hash - (str.charAt(i - x) * X_power) % MOD + MOD) % MOD;
            hash = (hash * X + str.charAt(i)) % MOD;
            // System.out.println(str.substring(i - x + 1, i + 1) + "의 해시: " + hash);
            if (set.contains(hash)) {
                return true;
            }

            set.add(hash);
        }
        return false;
    }
}