import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new HashMap<>();
        List<String> answer = new ArrayList<>();
        
        for(String order: orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            String sortedOrder = new String(chars);
            int len = sortedOrder.length();

            for(int size = 2; size <= order.length(); size++) {
                combinations(map, sortedOrder, new char[len], 0, 0, len, size);
            }
        }
        // System.out.println(map);
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        // System.out.println(entries);
        
        for(int c: course) {
            int maxCount = -1;
            for(var entry: entries) {
                int len = entry.getKey().length();
                if (maxCount == -1 && c == len) {
                    maxCount = entry.getValue();
                    if (maxCount >= 2) {
                        answer.add(entry.getKey());
                    }
                    continue;
                }
                if (c == len && maxCount == entry.getValue() && maxCount >= 2) {
                    answer.add(entry.getKey());
                }
            }
        }
        
        answer.sort(null);        
        return answer.toArray(new String[0]);
    }
    
    void combinations(Map<String, Integer> map, String original, char[] output, int start, int depth, int n, int r) {
        if (depth == r) {
            String key = new String(output, 0, r);
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }
        
        for(int i = start; i < n; i++) {
            output[depth] = original.charAt(i);
            combinations(map, original, output, i + 1, depth + 1, n, r);
        }
    }
}

class State {
    String str;
    int value;
}

// orders.length <= 20
// orders[0].length <= 20

// 요리 종류 최대 26개 -> 최대 조합: 10개 -> 조합 개수: 26C1 + 26C2 + ... + 26C10 -> 100만
// 1. orders 순회하며 2~10가지의 음식 조합 개수 더하기 -> 2^10 * 20 = 20,000
// 3. 다 꺼내서 Value 기준 정렬 -> N Log N -> 2만 * 15 = 30만
// 4. course 돌면서 course[i] 길이인것 중에 가장 value 큰 값들 다 넣기
