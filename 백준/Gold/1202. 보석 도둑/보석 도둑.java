import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Integer[] bag = new Integer[k];
        LinkedList<Gem> gems = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();
            gems.add(new Gem(m, v));
        }

        for (int i = 0; i < k; i++) {
            bag[i] = sc.nextInt();
        }

        Arrays.sort(bag);
        gems.sort(null);

        PriorityQueue<Gem> pq = new PriorityQueue<>((g1, g2) -> Integer.compare(g2.value, g1.value));
        long ans = 0;
        for (int i = 0; i < k; i++) {
            while (!gems.isEmpty()) {
                if (gems.peek().weight > bag[i]) {
                    break;
                }
                pq.offer(gems.poll());
            }
            if (!pq.isEmpty()) {
                ans += (long) pq.poll().value;
            }
        }
        System.out.println(ans);
    }
}

class Gem implements Comparable<Gem> {
    int weight, value;

    Gem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Gem other) {
        if (this.weight != other.weight) {
            return Integer.compare(this.weight, other.weight);
        } else {
            return Integer.compare(other.value, this.value);
        }
    }
}