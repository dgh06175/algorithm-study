import java.util.*;
import java.io.*;

public class Main {
    private static final int R = 31;
    private static final int M = 1_234_567_891;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int l = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();

        int hash = calcHash(str, l);
        System.out.println(hash);
    }

    private static int calcHash(String str, int len) {
        int hash = 0;
        int power = 1;
        for (int i = 0; i < len; i++) {
            int value = str.charAt(i) - 'a' + 1;
            hash = (int) ((hash + (long) value * power) % M);
            power = (int) ((long) power * R % M);
        }
        return hash % M;
    }
}
