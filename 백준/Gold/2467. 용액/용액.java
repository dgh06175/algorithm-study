import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] ary = new long[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Long.parseLong(st.nextToken());
        }
        long[] ans = new long[] { ary[0], ary[1] };
        long minDiff = Math.abs(ary[0] + ary[1]);
        Arrays.sort(ary);
        // 모든 용액 돌면서
        // 해당 용액과 더해서 가장 절댓값이 작은 용액 찾기 (ans에 저장)
        // 그 절댓값이 가장 작은 용액 두 쌍을 찾아야 함 (minDiff)
        // O(N)
        for (int i = 0; i < n; i++) {
            long x = ary[i];
            long y = find(ary, i);
            long diff = Math.abs(x + y);
            if (minDiff > diff) {
                minDiff = diff;
                ans[0] = x;
                ans[1] = y;
            }
        }

        if (ans[0] > ans[1]) {
            System.out.println(ans[1] + " " + ans[0]);
        } else {
            System.out.println(ans[0] + " " + ans[1]);
        }
    }

    // ary 중에 x 와 더해서 가장 절댓값이 작은 값 찾기
    // O(log(N))
    private static long find(long[] ary, int index) {
        long x = ary[index];
        int start = 0;
        int end = ary.length - 1;
        long minDiff = Long.MAX_VALUE;
        long ans = Long.MIN_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            long value = ary[mid] + x;
            long diff = Math.abs(value);
            if (minDiff > diff && mid != index) {
                minDiff = diff;
                ans = ary[mid];
            }
            if (value < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }
}
