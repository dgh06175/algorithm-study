import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
            String[] ary1 = sc.nextLine().split(" ");
            String[] ary2 = sc.nextLine().split(" ");

            // 모든 개수 합 - 둘이 set 한 개수 합
            int count = ary1.length + ary2.length;
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList(ary1));
            set.addAll(Arrays.asList(ary2));
            System.out.printf("#%d %d\n", test_case, count - set.size());
        }
    }
}