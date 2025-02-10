import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution {
    static class Node {
        int leftIndex;
        int rightIndex;
        char value;
        int number;

        Node(int l, int r, char v, int n) {
            this.leftIndex = l;
            this.rightIndex = r;
            this.value = v;
            this.number = n;
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            sc.nextLine();

            List<Node> tree = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                String[] input = sc.nextLine().split(" ");
                int num = Integer.valueOf(input[0]);
                char ch = input[1].toCharArray()[0];
                int left = 0;
                int right = 0;
                if (input.length > 2) {
                    left = Integer.valueOf(input[2]);
                }
                if (input.length > 3) {
                    right = Integer.valueOf(input[3]);
                }

                tree.add(new Node(left, right, ch, i));
            }
            System.out.printf("#%d ", test_case);
            printTreeInOrder(tree, tree.get(0));
            System.out.println();
        }
    }

    private static void printTreeInOrder(List<Node> tree, Node node) {
        if (node.leftIndex != 0) {
            printTreeInOrder(tree, tree.get(node.leftIndex - 1));
        }
        System.out.print(node.value);
        if (node.rightIndex != 0) {
            printTreeInOrder(tree, tree.get(node.rightIndex - 1));
        }
    }
}
