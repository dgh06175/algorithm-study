import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        // System.setIn(new FileInputStream("src/res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();

            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                list.add(sc.nextInt());
            }

            sc.nextLine();

            for (int i = 0; i < M; i++) {
                String[] input = sc.nextLine().split(" ");
                String order = input[0];
                int index = Integer.valueOf(input[1]);

                if (order.equals("I")) {
                    int value = Integer.valueOf(input[2]);
                    list.add(index, value);
                }
                if (order.equals("D")) {
                    list.remove(index);
                }
                if (order.equals("C")) {
                    int value = Integer.valueOf(input[2]);
                    list.set(index, value);
                }
            }

            if (list.size() <= L) {
                System.out.printf("#%d %d\n", test_case, -1);
                continue;
            }
            System.out.printf("#%d %d\n", test_case, list.get(L));
        }
    }
}