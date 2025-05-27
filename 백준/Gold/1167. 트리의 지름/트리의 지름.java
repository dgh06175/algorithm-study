import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Node>[] graph = new List[v + 1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1)
                    break;
                int weight = Integer.parseInt(st.nextToken());
                graph[cur].add(new Node(next, weight));
            }
        }
        Node farNode = dfs(graph, 1);
        Node ansNode = dfs(graph, farNode.num);
        System.out.println(ansNode.weight);
    }

    private static Node dfs(List<Node>[] graph, int start) {
        Node maxWeightNode = new Node(start, 0);
        Deque<Integer> stack = new ArrayDeque<>();
        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        stack.push(start);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (distance[cur] > maxWeightNode.weight) {
                maxWeightNode = new Node(cur, distance[cur]);
            }
            for (Node next : graph[cur]) {
                if (distance[next.num] > next.weight + distance[cur]) {
                    distance[next.num] = next.weight + distance[cur];
                    stack.push(next.num);
                }
            }
        }
        return maxWeightNode;
    }
}

class Node {
    int num;
    int weight;

    Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}