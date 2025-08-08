import java.util.*;

public class Main {
    static char[][] ary = new char[6144][6144];

    static void draw(int num, int y, int x) {
        if (num == 3) {
            ary[y][x] = '*';
            ary[y + 1][x - 1] = '*';
            ary[y + 1][x + 1] = '*';
            for (int i = 0; i < 5; i++) {
                ary[y + 2][x + i - 2] = '*';
            }
            return;
        }
        int n = num / 2;
        draw(n, y, x);
        draw(n, y + n, x - n);
        draw(n, y + n, x + n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        draw(n, 0, n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * 2; j++) { // 300만
                if (ary[i][j] == 0) {
                    sb.append(" ");
                } else {
                    sb.append(ary[i][j]);
                }
            }
            if (i == n - 1) {
                continue;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

// 24 호출 -> 12 를 3개
// 12 호출 -> 6을 3개
// 6 호출 -> 3을 3개
