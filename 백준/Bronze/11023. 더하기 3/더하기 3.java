import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static List<Integer> parseInputSplitToList(String splitRegex) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line, splitRegex);
        List<Integer> numbers = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            numbers.add(Integer.parseInt(tokenizer.nextToken()));
        }
        br.close();
        return numbers;
    }

    public static void main(String args[]) throws IOException {
        List<Integer> numbers = parseInputSplitToList(" ");
        int answer = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(answer);
    }
}
