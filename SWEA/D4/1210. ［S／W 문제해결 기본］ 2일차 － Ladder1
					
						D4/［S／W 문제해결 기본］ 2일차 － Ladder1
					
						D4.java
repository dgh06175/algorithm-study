import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static final int n = 100;
	
	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = run();
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
	
	static int run() throws Exception {
		br.readLine();
		short[][] ladder = new short[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				ladder[i][j] = Short.parseShort(st.nextToken());
			}
		}
		
		for(int x = 0; x < n; x++) {
			if (ladder[0][x] == 1 && go(ladder, x)) {
				return x;
			}
		}
		
		return -1;
	}
	
	static boolean go(short[][] ladder, int x) {
//		System.out.println("\n" + x + " 시작!");
		int y = 0;
		int d = 2; // 1: 우, 2: 아래, 3: 왼
		while (y < n) {
//			System.out.println(y + ", " + x);
			if (ladder[y][x] == 2) {
				return true;
			}
			
			if (y == n - 1) {
				return false;
			}
			
			if (d == 2) {
				if (x > 0 && ladder[y][x-1] == 1) {
					x--;
					d = 3;
					continue;
				}
				if (x < n - 1 && ladder[y][x+1] == 1) {
					x++;
					d = 1;
					continue;
				}
//				if (ladder[y+1][x] == 0) {
//					System.out.println("ERROR: " + ladder[y+1][x]);
//				}
				y++;
				continue;
			}
			
			
			
			if (ladder[y+1][x] == 1) {
				y++;
				d = 2;
				continue;
			}
			
			if (d == 1) {
//				if (ladder[y][x+1] != 1) {
//					System.out.println("ERROR");
//				}
				x++;
				continue;
			}
			if (d == 3) {
//				if (ladder[y][x-1] != 1) {
//					System.out.println("ERROR");
//				}
				x--;
			}
		}
		
		return false;
	}
}