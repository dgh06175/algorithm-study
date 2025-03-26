import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] ary, lis;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        lis = new int[n];
        lis[0] = ary[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (lis[len - 1] >= ary[i]) {
                int index = binarySearch(lis, len, ary[i]);
                lis[index] = ary[i]; // 덮어쓰기
            } else {
                lis[len++] = ary[i];
            }
        }
        System.out.println(len);
    }

    private static int binarySearch(int[] lis, int len, int target) {
        int start = 0;
        int end = len - 1;
        int ans = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (lis[mid] >= target) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
