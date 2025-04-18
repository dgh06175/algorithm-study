import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            if (check(input)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean check(char[] input) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            if (stack.isEmpty()) {
                stack.push(input[i]);
                continue;
            }

            if (stack.peek() == input[i]) {
                stack.pop();
            } else {
                stack.push(input[i]);
            }
        }
        return stack.isEmpty();
    }
}