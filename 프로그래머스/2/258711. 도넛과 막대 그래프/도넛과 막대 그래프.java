import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 각 정점의 inDegree와 outDegree를 계산
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            nodes.add(a);
            nodes.add(b);
            outDegree.put(a, outDegree.getOrDefault(a, 0) + 1);
            inDegree.put(b, inDegree.getOrDefault(b, 0) + 1);
        }

        // 생성된 정점 찾기 (나가는 간선만 있고 들어오는 간선이 없는 정점)
        int createdNode = -1;
        for (int node : nodes) {
            if (inDegree.getOrDefault(node, 0) == 0 && outDegree.getOrDefault(node, 0) >= 2) {
                createdNode = node;
                break;
            }
        }

        // 생성된 정점에서 연결된 그래프 탐색
        int donut = 0;
        int bar = 0;
        int eight = 0;

        for (int[] edge : edges) {
            if (edge[0] == createdNode) {
                int startNode = edge[1];
                if (isDonut(startNode, edges)) {
                    donut++;
                } else if (isBar(startNode, edges)) {
                    bar++;
                } else if (isEight(startNode, edges)) {
                    eight++;
                }
            }
        }

        return new int[]{createdNode, donut, bar, eight};
    }

    // 도넛 모양 그래프 판별
    private boolean isDonut(int startNode, int[][] edges) {
        Set<Integer> visited = new HashSet<>();
        int currentNode = startNode;
        while (true) {
            if (visited.contains(currentNode)) {
                return visited.size() > 1 && currentNode == startNode;
            }
            visited.add(currentNode);
            boolean foundNext = false;
            for (int[] edge : edges) {
                if (edge[0] == currentNode) {
                    currentNode = edge[1];
                    foundNext = true;
                    break;
                }
            }
            if (!foundNext) {
                return false;
            }
        }
    }

    // 막대 모양 그래프 판별
    private boolean isBar(int startNode, int[][] edges) {
        Set<Integer> visited = new HashSet<>();
        int currentNode = startNode;
        while (true) {
            if (visited.contains(currentNode)) {
                return false;
            }
            visited.add(currentNode);
            boolean foundNext = false;
            for (int[] edge : edges) {
                if (edge[0] == currentNode) {
                    currentNode = edge[1];
                    foundNext = true;
                    break;
                }
            }
            if (!foundNext) {
                return true;
            }
        }
    }

    // 8자 모양 그래프 판별
    private boolean isEight(int startNode, int[][] edges) {
        Set<Integer> visited = new HashSet<>();
        int currentNode = startNode;
        int cycleCount = 0;
        while (true) {
            if (visited.contains(currentNode)) {
                cycleCount++;
                if (cycleCount == 2) {
                    return true;
                }
                visited.clear();
            }
            visited.add(currentNode);
            boolean foundNext = false;
            for (int[] edge : edges) {
                if (edge[0] == currentNode) {
                    currentNode = edge[1];
                    foundNext = true;
                    break;
                }
            }
            if (!foundNext) {
                return false;
            }
        }
    }
}