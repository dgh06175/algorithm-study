import java.util.Scanner;

class Main {
    static int[][][] tetrominos = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } },
            { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }
    };

    static int[][][] generateAllVariants(int[][] shape) {
        int[][][] variants = new int[8][4][2];
        variants[0] = shape;

        for (int i = 1; i < 4; i++) {
            variants[i] = rotate(variants[i - 1]);
        }

        for (int i = 0; i < 4; i++) {
            variants[i + 4] = reflect(variants[i]);
        }

        return variants;
    }

    static int[][] rotate(int[][] shape) {
        int[][] rotated = new int[shape.length][2];
        for (int i = 0; i < shape.length; i++) {
            rotated[i][0] = shape[i][1];
            rotated[i][1] = -shape[i][0];
        }
        return rotated;
    }

    static int[][] reflect(int[][] shape) {
        int[][] reflected = new int[shape.length][2];
        for (int i = 0; i < shape.length; i++) {
            reflected[i][0] = -shape[i][0];
            reflected[i][1] = shape[i][1];
        }
        return reflected;
    }

    static boolean canPlace(int[][] paper, int[][] shape, int x, int y, int N, int M) {
        for (int[] block : shape) {
            int nx = x + block[0];
            int ny = y + block[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                return false;
            }
        }
        return true;
    }

    static int getSum(int[][] paper, int[][] shape, int x, int y) {
        int sum = 0;
        for (int[] block : shape) {
            sum += paper[x + block[0]][y + block[1]];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < tetrominos.length; i++) {
            int[][][] variants = generateAllVariants(tetrominos[i]);
            for (int v = 0; v < variants.length; v++) {
                int[][] shape = variants[v];
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        if (canPlace(paper, shape, x, y, N, M)) {
                            maxSum = Math.max(maxSum, getSum(paper, shape, x, y));
                        }
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}