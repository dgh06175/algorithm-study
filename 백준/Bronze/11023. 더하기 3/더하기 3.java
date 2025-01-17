import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class Main {
    public static List<Integer> parseInputSplitToList(String splitRegex) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(splitRegex);
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static void main(String args[]) throws IOException {
        List<Integer> numbers = parseInputSplitToList(" ");
        int answer = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(answer);
    }
}
