import java.io.*;
import java.util.*;

public class Main {
    static final int n = 9;
    static int[][] ans;
    static boolean solved = false;
    static int[] rowUsed = new int[n];
    static int[] colUsed = new int[n];
    static int[] blockUsed = new int[n];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[n][n];
        List<int[]> zeros = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = bf.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j) - '0';
                if (board[i][j] == 0) {
                    zeros.add(new int[] { i, j });
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    int num = board[i][j];
                    rowUsed[i] |= (1 << num);
                    colUsed[j] |= (1 << num);
                    blockUsed[getBlockIndex(i, j)] |= (1 << num);
                }
            }
        }

        solve(board, zeros, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }

    static int getBlockIndex(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    static void solve(int[][] board, List<int[]> zeros, int zeroIndex) {
        if (solved)
            return;

        if (zeroIndex == zeros.size()) {
            solved = true;
            ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                ans[i] = Arrays.copyOf(board[i], n);
            }
            return;
        }

        int y = zeros.get(zeroIndex)[0];
        int x = zeros.get(zeroIndex)[1];

        int[] pos = zeros.get(zeroIndex);
        int i = pos[0], j = pos[1];
        int blockIdx = getBlockIndex(i, j);

        for (int num = 1; num <= 9; num++) {
            int mask = 1 << num;
            if ((rowUsed[i] & mask) == 0 && (colUsed[j] & mask) == 0 && (blockUsed[blockIdx] & mask) == 0) {
                board[i][j] = num;
                rowUsed[i] |= mask;
                colUsed[j] |= mask;
                blockUsed[blockIdx] |= mask;

                solve(board, zeros, zeroIndex + 1);

                board[i][j] = 0;
                rowUsed[i] ^= mask;
                colUsed[j] ^= mask;
                blockUsed[blockIdx] ^= mask;
            }
        }
    }
}
