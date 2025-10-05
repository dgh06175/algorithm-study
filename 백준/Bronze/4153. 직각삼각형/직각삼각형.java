import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int[] num = new int[3];
            num[0] = sc.nextInt();
            num[1] = sc.nextInt();
            num[2] = sc.nextInt();
            if (num[0] == 0) {
                return;
            }

            Arrays.sort(num);
            if (num[0] * num[0] + num[1] * num[1] == num[2] * num[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}