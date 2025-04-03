import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
    int deadLine;
    int ramenCount;

    Problem(int deadLine, int ramenCount) {
        this.deadLine = deadLine;
        this.ramenCount = ramenCount;
    }

    @Override
    public int compareTo(Problem other) {
        if (this.deadLine == other.deadLine) {
            return Integer.compare(other.ramenCount, this.ramenCount); // 큰 순
        }
        return Integer.compare(this.deadLine, other.deadLine); // 작은 순
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> pq = new PriorityQueue<>();
        PriorityQueue<Problem> ansPQ = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.ramenCount, p2.ramenCount));

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.offer(new Problem(a, b));
        }

        int time = 1;
        while (!pq.isEmpty()) {
            Problem problem = pq.poll();

            // 되는걸 우선순위 큐에 넣고
            if (problem.deadLine >= time) {
                ansPQ.offer(problem);
                time += 1;
            } else { // 못넣었다면 우선순위 큐의 맨 위에 있는 컵라면 값이 현재 컵라면 값보다 작다면 바꾼다.
                if (!ansPQ.isEmpty() && ansPQ.peek().ramenCount < problem.ramenCount) {
                    ansPQ.poll();
                    ansPQ.offer(problem);
                }
            }
        }

        int ans = 0;
        while (!ansPQ.isEmpty()) {
            ans += ansPQ.poll().ramenCount;
        }
        System.out.println(ans);
    }
}