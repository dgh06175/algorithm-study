import java.util.*;

public class Main {
    static int n;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] ary = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ary[i][j] = sc.nextInt();
            }
        }

        calc(ary, 0);
        System.out.println(answer);
    }

    private static int[][] spin90(int[][] ary) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = new int[n];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[j][n - 1 - i] = ary[i][j];
            }
        }
        return copy;
    }

    private static int[][] spin180(int[][] ary) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = new int[n];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[n - 1 - i][n - 1 - j] = ary[i][j];
            }
        }
        return copy;
    }

    private static int[][] spin270(int[][] ary) {
        int[][] copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[n - 1 - j][i] = ary[i][j];
            }
        }
        return copy;
    }

    private static void calc(int[][] ary, int count) {
        if (count == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, ary[i][j]);
                }
            }
            return;
        }

        int[][] slide_up = slideUp(ary);
        calc(slide_up, count + 1);

        int[][] slide_right = spin90(ary);
        slide_right = slideUp(slide_right);
        slide_right = spin270(slide_right);
        calc(slide_right, count + 1);

        int[][] slide_down = spin180(ary);
        slide_down = slideUp(slide_down);
        slide_down = spin180(slide_down);
        calc(slide_down, count + 1);

        int[][] slide_left = spin270(ary);
        slide_left = slideUp(slide_left);
        slide_left = spin90(slide_left);
        calc(slide_left, count + 1);
    }

    private static int[][] slideUp(int[][] ary) {
        boolean[][] isMerged = new boolean[n][n];
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = Arrays.copyOf(ary[i], n);
        }

        for (int j = 0; j < n; j++) {
            for (int o = 0; o < n; o++) {
                for (int i = 0; i < n - 1; i++) {
                    if (copy[i][j] == copy[i + 1][j] && copy[i][j] != 0 && !isMerged[i][j] && !isMerged[i + 1][j]) {
                        copy[i][j] += copy[i + 1][j];
                        copy[i + 1][j] = 0;
                        isMerged[i][j] = true;
                    }
                    if (copy[i][j] == 0 && copy[i + 1][j] != 0) {
                        copy[i][j] = copy[i + 1][j];
                        copy[i + 1][j] = 0;

                        isMerged[i][j] = isMerged[i + 1][j];
                        isMerged[i + 1][j] = false;
                    }
                }
            }
        }
        return copy;
    }
}