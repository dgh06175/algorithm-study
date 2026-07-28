import java.util.*;

class Solution {
    public static String solution(int[] numbers) {
        int n = numbers.length;
        String[] ary = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .sorted((n1, n2) -> (n2 + n2 + n2).compareTo(n1 + n1 + n1))
            .toArray(String[]::new);
        
        
        StringBuilder sb = new StringBuilder();
        for(String s: ary) {
            sb.append(s);
        }
        String result = sb.toString();
        if (result.charAt(0) == '0') return "0";
        return result;
    }
}
