import java.util.*;

class Solution {
    static final int LEN = 8;
    List<char[]> permutations;
    
    public int solution(int n, String[] data) {
        char[][] inputAry = new char[n][4];
        for (int i = 0; i < n; i++) {
            char[] input = data[i].toCharArray();
            char x = input[0];
            char y = input[2];
            char oper = input[3];
            char gap = input[4];
            inputAry[i] = new char[] { x, y, oper, gap };
        }
        
        permutations = new ArrayList<>();
        char[] ary = new char[] { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
        perm(ary, new char[LEN], new boolean[LEN], 0, LEN, LEN);

        int ans = 0;
        for (char[] a : permutations) {
            boolean isValid = true;
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < LEN; i++) {
                map.put(a[i], i);
            }

            for (int i = 0; i < n; i++) {
                if (!checkIsValid(inputAry[i], a, map)) {
                    isValid = false;
                }
            }

            if (isValid) {
                ans += 1;
            }
        }
        return ans;
    }
    
    private boolean checkIsValid(char[] condition, char[] ary, Map<Character, Integer> map) {
        int x_pos = map.get(condition[0]);
        int y_pos = map.get(condition[1]);
        char oper = condition[2];
        int gap = condition[3] - '0';

        int x_y_gap = Math.abs(x_pos - y_pos) - 1;
        if (oper == '=') {
            return x_y_gap == gap;
        } else if (oper == '<') {
            return x_y_gap < gap;
        } else if (oper == '>') {
            return x_y_gap > gap;
        }

        return false;
    }

    private void perm(char[] arr, char[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            permutations.add(output.clone());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }
}