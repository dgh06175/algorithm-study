import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A 와 B 합친 경우의 수 모두 구하기
        int[] AB = new int[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[index++] = A[i] + B[j];
            }
        }

        // C 와 D 합친 경우의 수 모두 구하기
        int[] CD = new int[n * n];
        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[index++] = C[i] + D[j];
            }
        }

        // 두개 정렬 후 이분탐색으로 0되는 개수 찾기
        Arrays.sort(AB);
        Arrays.sort(CD);

        long ans = 0L;
        for (int i = 0; i < n * n; i++) {
            int target = -AB[i];
            ans += (long) (upperBound(CD, target) - lowerBound(CD, target));
        }
        System.out.println(ans);
    }

    static int lowerBound(int[] ary, int target) {
        int start = 0;
        int end = ary.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (ary[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    static int upperBound(int[] ary, int target) {
        int start = 0;
        int end = ary.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (ary[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}