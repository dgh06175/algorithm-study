import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        char[][] charOrders = new char[orders.length][];
        for(int i = 0; i < orders.length; i++) {
            charOrders[i] = orders[i].toCharArray();
            Arrays.sort(charOrders[i]);
        }
        
        for(int i = 0; i < charOrders.length; i++) {
            for(int r = 2; r <= charOrders[i].length; r++) {
                combinations(charOrders[i], new char[r], 0, 0, r);
            }
        }
        
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for(int c: course) {
            int maxValue = -1;
            for(Map.Entry<String, Integer> entry: entrySet) {
                String key = entry.getKey();
                int value = entry.getValue();
                if (c == key.length()) {
                    maxValue = Math.max(maxValue, value);
                }
            }
            
            if (maxValue < 2) continue;
            for(Map.Entry<String, Integer> entry: entrySet) {
                String key = entry.getKey();
                int value = entry.getValue();
                if (c == key.length() && maxValue == value) {
                    answer.add(key);
                }
            }
        }
        answer.sort(null);
        return answer.toArray(new String[0]);
    }
    
    void combinations(char[] order, char[] output, int depth, int start, int r) {
        if (depth == r) {
            String key = String.valueOf(output);
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }
        
        for(int i = start; i < order.length; i++) {
            output[depth] = order[i];
            combinations(order, output, depth + 1, i + 1, r);
        }
    }
}