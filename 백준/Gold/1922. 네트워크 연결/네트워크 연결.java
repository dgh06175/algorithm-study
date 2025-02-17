import java.util.*;
import java.io.*;

class Main
{
	static class Edge {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static class UnionFind {
		int[] parent;
		
		UnionFind(int size) {
			this.parent = new int[size];
			for(int i = 0; i < size; i++) {
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
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(bf.readLine());
		int M = Integer.valueOf(bf.readLine());
		
		UnionFind uf = new UnionFind(N + 1);
		List<Edge> edges = new ArrayList<>();
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			edges.add(new Edge(a, b, c));
		}
		
		edges.sort((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		
		int answer = 0;
		int count = 0;
		for(Edge edge: edges) {
			if (uf.union(edge.from, edge.to)) {
				answer += edge.cost;
				if (++count == N - 1) {
					break;
				}
			}
		}
		System.out.println(answer);
	}
}