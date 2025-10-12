import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(calclate(a, b, c));
    }

    // a^b%c 를 계산하는 함수
    // (a^b)%c = ( a^(b-x)%c * a^x%c )%c
    static long calclate(int a, int b, int c) {
        if (b == 0) {
            return 1;
        }
        long half = calclate(a, b / 2, c);
        long result = (half * half) % c;
        if (b % 2 == 0) {
            return result;
        }
        return (result * a) % c;
    }
}

// (10^11)%12 = ((10^5)%12 * (10^6)%12)%12
// 지수법칙 : a^(n+m) = a^n * a^m
// 모듈러 성질 : (a*b)%c = (a%c * b%c)%c