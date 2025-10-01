import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int v = sc.nextInt();
        v = v - a;
        System.out.println((int) (Math.ceil((double) v / (a - b)) + 1));
    }
}

// a = 5
// b = 1
// c = 6

// 5 4
// 9 8
//
// 11

// (c - a) / (a - b) + 1
// 6 / 2 = 3 + 1