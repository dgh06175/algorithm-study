import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static String solution(int[] numbers) {
        if (Arrays.stream(numbers).sum() == 0)
            return "0";
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> (b + a).compareTo(a + b))
                .collect(Collectors.joining(""));
    }
}
