import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/res/re_sample_input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(bf.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.printf("#%d", test_case);
            int N = Integer.valueOf(bf.readLine());
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int type = Integer.valueOf(st.nextToken());
                if (type == 1) {
                    int number = Integer.valueOf(st.nextToken());
                    pq.offer(number);
                } else {
                    int root;
                    if (pq.isEmpty()) {
                        root = -1;
                    } else {
                        root = pq.poll();
                    }
                    System.out.printf(" %d", root);
                }
            }
            System.out.println();
        }
    }
}