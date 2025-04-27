import java.io.IOException;
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Point p1 = new Point(sc.nextInt(), sc.nextInt());
        Point p2 = new Point(sc.nextInt(), sc.nextInt());
        Point p3 = new Point(sc.nextInt(), sc.nextInt());
        Point p4 = new Point(sc.nextInt(), sc.nextInt());

        int L1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int L2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        if (L1 == 0 && L2 == 0) {
            int min1x = Math.min(p1.x, p2.x), max1x = Math.max(p1.x, p2.x);
            int min2x = Math.min(p3.x, p4.x), max2x = Math.max(p3.x, p4.x);
            int min1y = Math.min(p1.y, p2.y), max1y = Math.max(p1.y, p2.y);
            int min2y = Math.min(p3.y, p4.y), max2y = Math.max(p3.y, p4.y);

            boolean xOverlap = Math.max(min1x, min2x) <= Math.min(max1x, max2x);
            boolean yOverlap = Math.max(min1y, min2y) <= Math.min(max1y, max2y);

            System.out.println((xOverlap && yOverlap) ? 1 : 0);
        } else {
            System.out.println((L1 <= 0 && L2 <= 0) ? 1 : 0);
        }
        sc.close();
    }

    static int ccw(Point p1, Point p2, Point p3) {
        long s = 1L * p1.x * p2.y + 1L * p2.x * p3.y + 1L * p3.x * p1.y;
        s -= 1L * p2.x * p1.y + 1L * p3.x * p2.y + 1L * p1.x * p3.y;

        if (s > 0) {
            return 1;
        } else if (s == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    static boolean overlap(Point p1, Point p2, Point p3, Point p4) {
        if (p1.x > p2.x || (p1.x == p2.x && p1.y > p2.y)) {
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        if (p3.x > p4.x || (p3.x == p4.x && p3.y > p4.y)) {
            Point tmp = p3;
            p3 = p4;
            p4 = tmp;
        }

        return !(p2.x < p3.x || p4.x < p1.x || p2.y < p3.y || p4.y < p1.y);
    }
}