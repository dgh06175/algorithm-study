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
        ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        // 이분탐색으로 길이: 1부터 n까지 탐색 O(logN)
        int start = 1;
        int end = n;
        int ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isValid(mid)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(ans);
    }

    // func: x의 길이로 합을 만들 수 있으면 true 반환 O(N)
    private static boolean isValid(int x) {
        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += ary[i];
        }
        for (int i = x; i < n; i++) {
            if (sum >= s) {
                return true;
            }
            sum += ary[i];
            sum -= ary[i - x];
        }
        if (sum >= s) {
            return true;
        }
        return false;
    }
}
