import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int n = sc.nextInt();
        City[] cities = new City[n];
        for (int i = 0; i < n; i++) {
            int cost = sc.nextInt();
            int customer = sc.nextInt();
            cities[i] = new City(cost, customer);
        }
        // ans[i] == 고객 i명을 늘이기 위해 투자해야 하는 최소 돈
        int[] ans = new int[c + 100];
        Arrays.fill(ans, Integer.MAX_VALUE / 100);
        ans[0] = 0;

        for (City city : cities) {
            if (ans[city.customer] > city.cost) {
                ans[city.customer] = city.cost;
            }
        }

        for (City city : cities) {
            for (int i = 1; i < c + 100; i++) {
                if (i - city.customer <= 0) {
                    continue;
                }
                ans[i] = Math.min(ans[i], ans[i - city.customer] + city.cost);
            }
        }

        int answer = ans[c];
        for (int i = c; i < c + 100; i++) {
            answer = Math.min(answer, ans[i]);
        }

        System.out.println(answer);
    }
}

class City {
    int cost;
    int customer;

    City(int c, int cu) {
        this.cost = c;
        this.customer = cu;
    }
}