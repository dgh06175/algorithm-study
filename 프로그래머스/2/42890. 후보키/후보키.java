import java.util.*;

class Solution {
    List<Set<Integer>> keys = new ArrayList<>();
    int row, col;
    boolean[] visited;
    
    public int solution(String[][] relation) {
        col = relation.length;
        row = relation[0].length;
        visited = new boolean[row];
        for(int i = 1; i <= col; i++) {
            getCombinations(0, i, relation);
        }

        int answer = 0;
        for (int i = 0; i < keys.size(); i++) {
            boolean isContains = false;
            for (int j = 0; j < keys.size(); j++) {
                if (i == j) continue;
                if (keys.get(i).containsAll(keys.get(j))) {
                    isContains = true;
                }
            }
            if (!isContains) {
                answer++;
            }
        }
        return answer;
    }
    
    void getCombinations(int start, int r, String[][] relation) {
        if (r == 0) {
            Set<Integer> tmp = new HashSet<>();
            for(int i = 0; i < row; i++) {
                if (visited[i]) {
                    tmp.add(i);
                }
            }
            
            Set<String> set = new HashSet<>();
            // 모든 col 에서 visited = true 인 값에 대해 더하기 연산 수행
            for(int i = 0; i < col; i++) {
                String temp = "";
                for(int j = 0; j < row; j++) {
                    if (visited[j]) {
                        temp += relation[i][j];
                    }
                }
                set.add(temp);
            }
            
            if (set.size() == col) {
                keys.add(tmp);
            }
        }
        
        for(int i = start; i < row; i++) {
            if (!visited[i]) {
                visited[i] = true;
                getCombinations(i + 1, r - 1, relation);
                visited[i] = false;
            }
        }
    }
}