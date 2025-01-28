import java.util.*;
import java.util.stream.*;

class Solution {
    private int compare(Integer a, Integer b) {
        String strA = String.valueOf(a);
        String strB = Integer.toString(b);
        int tmp1 = Integer.valueOf(strA + strB);
        int tmp2 = Integer.valueOf(strB + strA);
        return tmp1 - tmp2;
    }
    
    public String solution(int[] numbers) {
        Integer[] ary = Arrays.stream(numbers)
            .mapToObj(Integer::valueOf)
            .toArray(Integer[]::new);
        String answer = "";
        Arrays.sort(ary, (x1, x2) -> compare(x2, x1));
        int zeroCount = 0;
        for(var m: ary){
            answer += m;
            if (m == 0) {
                zeroCount ++;
            }
        }
        if (zeroCount == ary.length) {
            return "0";
        }
        return answer;
    }
}