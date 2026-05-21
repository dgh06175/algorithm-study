import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] ary = new int[n];
			for(int i = 0; i < n; i++) { // 1,000,000
				ary[i] = Integer.parseInt(st.nextToken());
			}
			
			// 뒤에서부터 오면서 최대값 기록, 갱신 시 지금까지 온 날짜만큼 팔기. -> 최대 2백만
			long ans = 0;
			int max = ary[n - 1];
			for(int i = n - 1; i >= 0; i--) {
				if (max > ary[i]) {
					ans += max - ary[i];
				} else if (max < ary[i]) {
					max = ary[i];
				}
			}
			
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}