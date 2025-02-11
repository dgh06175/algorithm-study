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
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		List<Edge> edges = new ArrayList<>();
		while(M-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			edges.add(new Edge(a, b, c));
		}
		
		int[] parent = new int[N + 1];
		int[] rank = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		edges.sort((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		int answer = 0;
		int count = 0;
		for(Edge edge: edges) {
			if (find(parent, edge.from) != find(parent, edge.to)) {
				union(parent, rank, edge.from, edge.to);
				answer += edge.cost;
				if (count++ == N - 1) {
					break;
				}
			}
		}
		System.out.println(answer);
	}
	
    static int find(int[] parent, int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent, parent[x]);
	}
	
	static void union(int[] parent, int[] rank, int a, int b) {
		int rootA = find(parent, a);
		int rootB = find(parent, b);
		
		if (rootA == rootB) {
            return;
        }
        
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
	}
}