import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.printf("#%d %d\n", test_case, run());
		}
	}
	
	static int run() throws Exception {
		int len = Integer.parseInt(br.readLine());
		char[][] map = new char[8][8];
		for(int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int ans = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (i + len - 1 < 8) {
					int start = i;
					int end = i + len - 1;
					boolean isPen = true;
					while (start < end) {
						if (map[start][j] != map[end][j]) {
							isPen = false;
							break;
						}
						start++;
						end--;
					}
					if (isPen) {
						ans++;
					}
				}
				if (j + len - 1 < 8) {
					int start = j;
					int end = j + len - 1;
					boolean isPen = true;
					while (start < end) {
						if (map[i][start] != map[i][end]) {
							isPen = false;
							break;
						}
						start++;
						end--;
					}
					if (isPen) {
						ans++;
					}
				}
			}
		}
		return ans;
	}
}