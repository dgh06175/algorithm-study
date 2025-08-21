import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] input = sc.nextLine().toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : input) {
            if (Character.isAlphabetic(c)) {
                sb.append(c);
                continue;
            }

            if (c == '*' || c == '/') {
                while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                    sb.append(stack.pop());
                }
                stack.push(c);
                continue;
            }

            if (c == '+' || c == '-') {
                while (!stack.isEmpty()
                        && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '+' || stack.peek() == '-')) {
                    sb.append(stack.pop());
                }
                stack.push(c);
                continue;
            }

            if (c == '(') {
                stack.push(c);
                continue;
            }

            if (c == ')') {
                while (true) {
                    char head = stack.pop();
                    if (head == '(') {
                        break;
                    }
                    sb.append(head);
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}