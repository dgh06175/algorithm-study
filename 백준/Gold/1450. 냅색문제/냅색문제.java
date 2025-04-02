import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] weight;
    static List<Integer> leftSum = new ArrayList<>();
    static List<Integer> rightSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 30
        C = Integer.parseInt(st.nextToken()); // 10^9
        weight = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        int[] leftWeight = Arrays.copyOfRange(weight, 0, N / 2);
        int[] rightWeight = Arrays.copyOfRange(weight, N / 2, N);

        List<Integer> leftSum = new ArrayList<>();
        List<Integer> rightSum = new ArrayList<>();

        calcSubSum(leftSum, leftWeight, 0, 0);
        calcSubSum(rightSum, rightWeight, 0, 0);

        leftSum.sort(null);
        rightSum.sort(null);

        int count = meetInTheMiddle(leftSum, rightSum);

        System.out.println(count);
    }

    private static void calcSubSum(List<Integer> subSum, int[] ary, int index, int sum) {
        if (sum > C) {
            return;
        }
        if (index >= ary.length) {
            subSum.add(sum);
            return;
        }

        calcSubSum(subSum, ary, index + 1, sum + ary[index]);
        calcSubSum(subSum, ary, index + 1, sum);
    }

    private static int meetInTheMiddle(List<Integer> leftSum, List<Integer> rightSum) {
        int count = 0;
        for (int i : leftSum) {
            // rightSum 에서 i + rightSum[index] 가 C 보다 같거나 작은 값 중 최대 값 찾기
            int ans = -1;
            int start = 0;
            int end = rightSum.size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (i + rightSum.get(mid) <= C) {
                    start = mid + 1;
                    ans = mid;
                } else {
                    end = mid - 1;
                }
            }

            if (ans == -1) {
                continue;
            }

            count += ans + 1;
        }

        return count;
    }
}