import java.util.*;

class Solution {
    int n;
    int[] preAry;
    int[] postAry;
    int preIdx = 0;
    int postIdx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        n = nodeinfo.length;
        preAry = new int[n];
        postAry = new int[n];
        
        int[][] answer = {};
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i]);
        }
        
        Arrays.sort(nodes, (a, b) -> Integer.compare(b.y, a.y));
        
        for(int i = 1; i < n; i++) {
            insertNode(nodes[0], nodes[i]);
        }
        
        preOrder(nodes[0]);
        postOrder(nodes[0]);
        
        return new int[][]{preAry, postAry};
    }
    
    void insertNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }
    
    void preOrder(Node node) {
        if (node == null) return;
        preAry[preIdx++] = node.id;
        preOrder(node.left);
        preOrder(node.right);
    }
    
    void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        postAry[postIdx++] = node.id;
    }
}

class Node {
    int id;
    int x;
    int y;
    Node left;
    Node right;
    
    Node(int id, int[] node) {
        this(id, node[0], node[1]);
    }
    
    Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        left = null;
        right = null;
    }
}