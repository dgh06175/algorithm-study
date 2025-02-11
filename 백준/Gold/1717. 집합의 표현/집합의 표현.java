import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Main
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		while(M-- > 0) {
			int type = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
				
			if (type == 0) {
				union(parent, a, b);
			} else if (type == 1) {
			    if (find(parent, a) == find(parent, b)) {
			    	System.out.println("YES");
			    } else {
			    	System.out.println("NO");
			    }
			}
		}
	}
	
	static void union(int[] parent, int a, int b) {
		int rootA = find(parent, a);
	    int rootB = find(parent, b);
		    
		if (rootA == rootB) {
			return;
		}
		parent[rootB] = rootA;
	}
	
	static int find(int[] parent, int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent, parent[x]);
	}
}