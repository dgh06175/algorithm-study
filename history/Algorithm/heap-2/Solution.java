import java.util.*;
import java.io.*;

class Solution {
    static HashMap<Integer, Integer> memo;

    public static void main(String args[]) throws Exception {
        // System.setIn(new FileInputStream("src/res/sample_input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(bf.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int[] ary = new int[N];
            for (int i = 0; i < N; i++) {
                ary[i] = Integer.valueOf(st.nextToken());
            }
            int K = Integer.valueOf(bf.readLine());
            memo = new HashMap<>();
            System.out.printf("#%d %d\n", test_case, getMinStep(K, ary));
        }
    }

    // k = (K-X)/D
    static int getMinStep(int k, int[] ary) {
        if (k == 0) {
            return 0;
        }

        if (memo.containsKey(k)) {
            return memo.get(k);
        }

        int count = k;
        for (int a : ary) {
            int newK = (k - k % a) / a;
            int remainMinStep = k % a + getMinStep(newK, ary);
            count = Math.min(count, remainMinStep);
        }
        memo.put(k, count);
        return count;
    }
}