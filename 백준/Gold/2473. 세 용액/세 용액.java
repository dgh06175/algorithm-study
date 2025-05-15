import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ary = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken()); // -10억 ~ 10억
        }
        // 원본 배열 정렬
        Arrays.sort(ary);
        long minSum = Long.MAX_VALUE;
        int[] result = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE };

        // 한 수 고정하고, 나머지 두 값과 합해서 0에 가까운 값 찾기
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = (long) ary[i] + ary[left] + ary[right];

                if (Math.abs(sum) < Math.abs(minSum)) {
                    minSum = sum;
                    result[0] = ary[i];
                    result[1] = ary[left];
                    result[2] = ary[right];
                }

                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}