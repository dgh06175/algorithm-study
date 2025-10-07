import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sizes = new int[6];
        for (int i = 0; i < 6; i++) {
            sizes[i] = sc.nextInt();
        }
        int T = sc.nextInt();
        int P = sc.nextInt();
        int[] sizesAns = new int[6];
        for (int i = 0; i < 6; i++) {
            sizesAns[i] = sizes[i] / T;
            if (sizes[i] % T != 0) {
                sizesAns[i] += 1;
            }
        }
        System.out.println(Arrays.stream(sizesAns).sum());
        int p = n / P;
        System.out.println(p + " " + (n - (n / P) * P));
    }
}
// S T
// 6벌, 4개가 묶음 -> 2묶음
// 4벌, 4개가 묶음 -> 1묶음
// S / T + (if S % T != 0) += 1