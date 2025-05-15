import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        Arrays.sort(ary);
        long minSum = Long.MAX_VALUE;
        int[] result = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };

        int left = 0;
        int right = n - 1;

        while (left < right) {
            long sum = (long) ary[left] + ary[right];

            if (Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                result[0] = ary[left];
                result[1] = ary[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        for (int i = 0; i < 2; i++) {
            System.out.print(result[i] + " ");
        }
    }
}