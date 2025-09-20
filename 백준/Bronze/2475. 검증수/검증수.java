import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        for(int i = 0; i < 5; i++) {
            int num = sc.nextInt();
            ans += (num * num) % 10;
        }
        System.out.println(ans % 10);
    }
}