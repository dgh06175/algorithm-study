import java.util.*;

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
		int[] rank;
		
		UnionFind(int size) {
			this.parent = new int[size];
			for(int i = 0; i < size; i++) {
				parent[i] = i;
			}
			this.rank = new int[size];
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
	        
	        if (rank[rootA] < rank[rootB]) {
	            parent[rootA] = rootB;
	        } else if (rank[rootA] > rank[rootB]) {
	            parent[rootB] = rootA;
	        } else {
	            parent[rootB] = rootA;
	            rank[rootA]++;
	        }
	        return true;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		UnionFind uf = new UnionFind(N + 1);
		List<Edge> edges = new ArrayList<>();
		while(M-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
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