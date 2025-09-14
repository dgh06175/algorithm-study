import java.util.*;
import java.io.IOException;

public class Main {
    static int[] f;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        Pair ans = fibo(n);
        System.out.println(ans.x);
    }

    // 짝수: f(2n) = f(n) * (2f(n+1) - f(n))
    // 홀수: f(2n + 1) = f(n + 1)^2 + f(n)^2
    static Pair fibo(long n) {
        if (n == 0L) {
            return new Pair(0, 1);
        }

        Pair ab = fibo(n / 2);
        long a = ab.x;
        long b = ab.y;

        long c = (a * ((2 * b % MOD - a + MOD) % MOD)) % MOD;
        long d = (a * a % MOD + b * b % MOD) % MOD;

        if (n % 2 == 0) {
            return new Pair(c, d);
        } else {
            return new Pair(d, c + d);
        }
    }
}

// f(n), f(n+1)
class Pair {
    long x;
    long y;

    Pair(long x, long y) {
        this.x = x;
        this.y = y;
    }
}