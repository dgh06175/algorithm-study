import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;
        
        Stack<Integer> removed = new Stack<>();
        int cur = k;

        for (String c : cmd) {
            char command = c.charAt(0);
            if (command == 'U') {
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) {
                    cur = prev[cur];
                }
            } else if (command == 'D') {
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) {
                    cur = next[cur];
                }
            } else if (command == 'C') {
                removed.push(cur);
                if (prev[cur] != -1) {
                    next[prev[cur]] = next[cur];
                }
                if (next[cur] != -1) {
                    prev[next[cur]] = prev[cur];
                }
                cur = (next[cur] != -1) ? next[cur] : prev[cur];
            } else if (command == 'Z') {
                int restore = removed.pop();
                if (prev[restore] != -1) {
                    next[prev[restore]] = restore;
                }
                if (next[restore] != -1) {
                    prev[next[restore]] = restore;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean[] isRemoved = new boolean[n];
        for (int idx : removed) {
            isRemoved[idx] = true;
        }
        for (int i = 0; i < n; i++) {
            sb.append(isRemoved[i] ? 'X' : 'O');
        }

        return sb.toString();
    }
}
