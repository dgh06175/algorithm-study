import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    int calcDist(Point other) {
        return Math.abs(y - other.y) + Math.abs(x - other.x);
    }
}

public class Main {
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Point> houseList = new ArrayList<>();
        List<Point> chickenList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int input = sc.nextInt();
                if (input == 1) {
                    houseList.add(new Point(i, j));
                } else if (input == 2) {
                    chickenList.add(new Point(i, j));
                }
            }
        }

        dfs(houseList, chickenList, m, 0, new Stack<>());
        System.out.println(ans);
    }

    private static void dfs(
            List<Point> houseList,
            List<Point> chickenList,
            int m,
            int index,
            Stack<Point> current) {

        if (current.size() == m) {
            int val = calc(houseList, current);
            ans = Math.min(val, ans);
            return;
        }

        if (index >= chickenList.size()) {
            return;
        }

        // 현재 요소 선택
        current.push(chickenList.get(index));
        dfs(houseList, chickenList, m, index + 1, current);
        // 취소
        current.pop();

        // 현재 요소 선택 안함
        dfs(houseList, chickenList, m, index + 1, current);
    }

    private static int calc(List<Point> houseList, List<Point> current) {
        int chickenDistance = 0;
        for (Point house : houseList) {
            int minDist = Integer.MAX_VALUE;
            for (Point chicken : current) {
                minDist = Math.min(minDist, house.calcDist(chicken));
            }
            chickenDistance += minDist;
        }
        return chickenDistance;
    }
}