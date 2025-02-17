import java.util.Scanner;

class Main {
	static class UnionFind {
        int[] parent;
        
        UnionFind(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        
        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            
            if (rootA == rootB) {
                return false;
            }
            
            if (rootA <= rootB) {
                parent[rootB] = rootA;
            } else {
                parent[rootA] = rootB;
            }
            return true;
        }
    }
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        UnionFind uf = new UnionFind(N + 1);
        
        while (M-- > 0) {
            int type = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            if (type == 0) {
                uf.union(a, b);
            } else if (type == 1) {
                if (uf.find(a) == uf.find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
        
        sc.close();
    }
}
