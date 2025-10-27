import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(bf.readLine()));
        for (int i = 0; i < 10000; i++) {
            String str = bf.readLine();
            if (str == null) {
                break;
            }
            int a = Integer.parseInt(str);
            root.insert(a);
        }
        root.print();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    void insert(int v) {
        if (v < value) {
            if (left == null) {
                this.left = new Node(v);
            } else {
                left.insert(v);
            }
        } else {
            if (right == null) {
                this.right = new Node(v);
            } else {
                right.insert(v);
            }
        }
    }

    void print() {
        if (left != null) {
            left.print();
        }
        if (right != null) {
            right.print();
        }
        System.out.println(value);
    }
}
