import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	static final int n = 16;
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, 1, -1};

	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			boolean ans = run();
			System.out.printf("#%d %d\n", test_case, ans ? 1 : 0);
		}
	}
	
	static boolean run() throws IOException {
		br.readLine();
		
		boolean[][] graph = new boolean[n][n];
		int y = -1, x = -1;
		int ans_y = -1, ans_x = -1;
		
		for(int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < n; j++) {
				int num = input[j] - '0';
				graph[i][j] = (num == 1);
				if (num == 2) {
					y = i;
					x = j;
				} else if (num == 3) {
					ans_y = i;
					ans_x = j;
				}
			}
		}
		
		if (y == -1 || x == -1 || ans_y == -1 || ans_x == -1) {
		    System.out.println("ERROR");
		    return false;
		}
		
		return bfs(graph, y, x, ans_y, ans_x);
	}
	
	static boolean bfs(boolean[][] graph, int start_y, int start_x, int ans_y, int ans_x) {
		boolean[][] visited = new boolean[n][n];
		visited[start_y][start_x] = true;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {start_y, start_x});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int y = now[0];
			int x = now[1];
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
					continue;
				}
				if (visited[ny][nx]) {
					continue;
				}
				
				if (ny == ans_y && nx == ans_x) {
					return true;
				}
				
				if (!graph[ny][nx]) {
					queue.offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
		}
		return false;
	}
}