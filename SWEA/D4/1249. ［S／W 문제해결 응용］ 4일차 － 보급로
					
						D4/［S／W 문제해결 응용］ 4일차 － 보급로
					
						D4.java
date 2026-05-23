import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, 1, -1};
	static final int MAX_VALUE = 1_000_000;

	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = run();
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
	
	static int run() throws IOException {
		int n = Integer.parseInt(br.readLine());
		
		int[][] graph = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < n; j++) {
				graph[i][j] = input[j] - '0';
			}
		}
		
		
		return dijkstra(graph, n);
	}
	
	static int dijkstra(int[][] graph, int n) {
		int[][] dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], MAX_VALUE);
		}
		Queue<Point> queue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.cost, p2.cost));
		
		queue.offer(new Point(0, 0, 0));
		dist[0][0] = 0;
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			if (dist[now.y][now.x] < now.cost) {
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
					continue;
				}
				
				int next_cost = now.cost + graph[ny][nx];
				if (dist[ny][nx] > next_cost) {
					dist[ny][nx] = next_cost;
					queue.offer(new Point(ny, nx, next_cost));
				}
			}
		}
		return dist[n-1][n-1];
	}
	
	static class Point {
		int y;
		int x;
		int cost;
		
		Point(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}
}