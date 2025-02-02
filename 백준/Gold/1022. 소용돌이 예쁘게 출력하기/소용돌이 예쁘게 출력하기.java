import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();

        printAry(r1, c1, r2, c2);
    }

    private static void printAry(int r1, int c1, int r2, int c2) {
        int maxNum = 0;
        int[][] ary = new int[r2-r1 + 1][c2-c1 + 1];
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                int num = getNumber(i, j);
                ary[i - r1][j - c1] = num;
                maxNum = Math.max(maxNum, num);
            }
        }
        int maxNumLength = String.valueOf(maxNum).length();
        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                System.out.printf("%" + maxNumLength + "d ", ary[i][j]);
            }
            System.out.println();
        }
    }

    private static int getNumber(int y, int x) {
        int floor = Math.max(Math.abs(y), Math.abs(x));
        int startNumber = (floor * 2 - 1) * (floor * 2 - 1) + 1; // 2, 10, 26 같이 계층 별 시작 숫자
        // y == x 이고 자연수일때 (2 * x + 1) * (2 * x + 1)
        if (y == x && y > 0) {
            return (2 * x + 1) * (2 * x + 1);
        }

        // (1, 2) 가 10일 때 (-2, 2) 는 13 -> x == floor 일 때 num = startNumber + floor - y - 1
        if (x == floor) {
            return startNumber + floor - y - 1;
        }

        // (1, 2) 가 10일 때 (-2, 0) 은 15 -> y == -floor 일 때 num = startNumber + (floor * 2 - 1) + floor - x
        if (y == -floor) {
            return startNumber + floor * 2 - 1 + floor - x;
        }

        // (1, 2) 가 10일 때 (-1, -2)는 18 -> x == -floor 일 때 num = startNumber + (floor * 2 * 2 - 1) + y + floor
        if (x == -floor) {
            return startNumber + floor * 2 * 2 - 1 + y + floor;
        }

        // (1, 2) 가 10일 때 (2, 1) 은. 24 -> y == floor 일 때 num = startNumber + (floor * 2 * 3 - 1) + floor + x
        if (y == floor) {
            return startNumber + floor * 2 * 3 - 1 + floor + x;
        }

        return 0;
    }
}
//     -3 -2 -1  0  1  2  3
//     --------------------
// -3 |37 36 35 34 33 32 31
// -2 |38 17 16 15 14 13 30
// -1 |39 18  5  4  3 12 29
//  0 |40 19  6  1  2 11 28
//  1 |41 20  7  8  9 10 27
//  2 |42 21 22 23 24 25 26
//  3 |43 44 45 46 47 48 49