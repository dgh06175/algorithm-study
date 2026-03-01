import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int b = sc.nextInt();
        int c = sc.nextInt();

        long answer = n;
        for (int i = 0; i < n; i++) {
            a[i] -= b;
            if (a[i] <= 0) {
                continue;
            }

            answer += (a[i] - 1) / c + 1;
        }
        System.out.println(answer);
    }
}