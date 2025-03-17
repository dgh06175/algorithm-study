import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int k = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            int len = str.length();
            String[] suffix = new String[len];
            for (int i = 0; i < len; i++) {
                suffix[i] = str.substring(i);
            }
            Arrays.sort(suffix);
            System.out.printf("#%d %s\n", test_case, suffix[k - 1]);
        }
    }
}