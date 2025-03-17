import java.util.*;

public class Solution {
    static int n;
    static List<List<Integer>> allPaths = new ArrayList<>();
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) {
        n = 5; // 도시 수
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(3);

        // 시작 도시(0)에서 목표 도시(3)로 향하는 모든 경로를 찾음
        findPaths(0, 3);

        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }
    }

    static void findPaths(int start, int end) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> path = new ArrayList<>();
        dfs(start, end, visited, path);
    }

    static void dfs(int current, int end, boolean[] visited, List<Integer> path) {
        visited[current] = true;
        path.add(current);

        if (current == end) {
            allPaths.add(new ArrayList<>(path));
        } else {
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    dfs(next, end, visited, path);
                }
            }
        }

        path.remove(path.size() - 1);
        visited[current] = false;
    }
}
