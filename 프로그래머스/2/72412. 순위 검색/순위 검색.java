import java.util.*;

class Solution {
    HashMap<String, List<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        for(String in: info) {
            addInfo(in);
        }

        // 모든 배열 정렬
        for(var v: map.values()) {
            v.sort(null);
        }
        
        // 쿼리 + 이진탐색
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            answer[i] = getQuery(query[i]);
        }
        
        return answer;
    }
    
    // "cpp and - and senior and pizza 250"
    // "- and - and - and - 150"
    int getQuery(String query) {
        StringTokenizer st = new StringTokenizer(query);
        String lang = st.nextToken();
        st.nextToken();
        String job = st.nextToken();
        st.nextToken();
        String history = st.nextToken();
        st.nextToken();
        String food = st.nextToken();
        int point = Integer.parseInt(st.nextToken());
        String key = lang + job + history + food;
        // String key = convertToKey(lang, job, history, food);
        List<Integer> ary = map.get(key);
        if (ary == null) return 0;
        int start = 0;
        int end = ary.size() - 1;
        int ans = ary.size();
        while (start <= end) {
            int mid = (start + end) / 2;
            if (ary.get(mid) >= point) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ary.size() - ans;
    }
    
    // "java backend junior pizza 150"
    void addInfo(String info) {
        StringTokenizer st = new StringTokenizer(info);
        String lang = st.nextToken();
        String job = st.nextToken();
        String history = st.nextToken();
        String food = st.nextToken();
        int point = Integer.parseInt(st.nextToken());
        String[] parts = new String[] {lang, job, history, food};
        List<String> combinations = new ArrayList<>();
        generateCombinations(parts, 0, "", combinations);
        for(String key: combinations) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(point);
        }
    }
    
    void generateCombinations(String[] parts, int index, String path, List<String> combinations){
        if (index == 4) {
            combinations.add(path);
            return;
        }
        
        generateCombinations(parts, index + 1, path + parts[index], combinations);
        generateCombinations(parts, index + 1, path + "-", combinations);
    }
    
    // String convertToKey(String lang, String job, String history, String food) {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(lang);
    //     sb.append(job);
    //     sb.append(history);
    //     sb.append(food);
    //     return sb.toString();
    // }
}