import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("src/res/input (1).txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T = Integer.parseInt(sc.nextLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(sc.nextLine());
			int[][] map = new int[n][n];
			for(int i = 0; i < n; i++) {
				map[i] = Arrays.stream(sc.nextLine().split(""))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			int answer = getMinDist(n, map);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	static int getMinDist(int n, int[][] map) {
		int[][] dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		dist[0][0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.dist, n2.dist));
		pq.offer(new Node(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int y = cur.y;
			int x = cur.x;
			int curDist = cur.dist;
			
			if (dist[y][x] < curDist) continue;
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				int newDist = dist[y][x] + map[ny][nx];
				
				if (newDist < dist[ny][nx]) {
					dist[ny][nx] = newDist;
					pq.offer(new Node(ny, nx, newDist));
				}
			}
		}
		
		return dist[n - 1][n - 1];
	}
	
	static class Node {
		int y, x, dist;
		Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}