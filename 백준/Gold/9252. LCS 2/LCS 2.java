import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str1 = sc.nextLine().toCharArray();
        char[] str2 = sc.nextLine().toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;

        // dp[i][j] = str1[0 ~ i], str2[0 ~ j] 까지 최대 lcs 의 길이
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[len1][len2]);
        StringBuilder sb = new StringBuilder();
        int y = len1;
        int x = len2;
        while (y > 0 && x > 0) {
            if (str1[y - 1] == str2[x - 1]) {
                sb.append(str1[y - 1]);
                y--;
                x--;
            } else {
                if (dp[y - 1][x] > dp[y][x - 1]) {
                    y--;
                } else {
                    x--;
                }
            }
        }
        System.out.println(sb.reverse());
    }
}
