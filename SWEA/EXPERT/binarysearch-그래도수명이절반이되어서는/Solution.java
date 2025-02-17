import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/res/sample_input (2).txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] W = new int[N];
            int[] S = new int[K];
            int sum = 0;
            for (int i = 0; i < N; i++) {
                W[i] = sc.nextInt();
            }
            for (int i = 0; i < K; i++) {
                S[i] = sc.nextInt();
                sum += S[i];
            }
            int answer = getMinWearLevel(W, S, N, K, sum);
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    // O(NLogN)
    static int getMinWearLevel(int[] w, int[] s, int n, int k, int sum) {
        int start = 1;
        int end = 200000;
        boolean result = false;

        while (start <= end) {
            int mid = (start + end) / 2;
            boolean valid = calcInsertValid(w, s, n, k, mid, sum);
            // 반환값이 true 면 왼쪽 구역 탐색 (end = mid - 1)
            if (valid) {
                end = mid - 1;
            } else { // 반환값이 false 면 오른쪽 구역 탐색 (start = mid + 1)
                start = mid + 1;
            }
            // start == end 가 되면 최근 결과값이 true 였으면 답은 start + 1, false 였으면 start
        }
        if (result) {
            return start + 1;
        }
        return start;
    }

    // O(N)
    static boolean calcInsertValid(int[] w, int[] s, int n, int k, int level, int sum) {
        int s_index = 0;
        int setBlockCounts = 0;

        for (int i = 0; i < n; i++) {
            // 남은 넣어햐하는 크기가 남은 칸 수 보다 많으면 실패
            if (n - i < sum - setBlockCounts) {
                return false;
            }
            boolean isFit = true;
            for (int j = 0; j < s[s_index]; j++) {
                if (i + j >= n || w[i + j] > level) {
                    isFit = false;
                    break;
                }
            }
            if (isFit) {
                setBlockCounts += s[s_index];
                i += s[s_index] - 1;
                s_index++;
            }
            if (s_index == k) {
                return true;
            }
        }
        return false;
    }
}