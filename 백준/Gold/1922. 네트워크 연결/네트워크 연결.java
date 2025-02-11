import java.util.*;

class Main
{
	static class Edge{
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
		
		int[] parent = new int[N + 1];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		while(M-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			edges.add(new Edge(a, b, c));
		}
		
		edges.sort((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		int answer = 0;
		for(Edge edge: edges) {
			if (find(parent, edge.from) != find(parent, edge.to)) {
				union(parent, edge.from, edge.to);
				answer += edge.cost;
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
	
	static void union(int[] parent, int a, int b) {
		int rootA = find(parent, a);
		int rootB = find(parent, b);
		
		if (rootA != rootB) {
			parent[rootA] = rootB;
		}
	}
}