import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("src/res/re_sample_input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(bf.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			long target = Long.parseLong(bf.readLine());
			
			long left = 0;
			long right = Math.round(Math.pow(10, 10));
			long k = -1;
			
			while (left <= right) {
				long mid = (right + left) / 2;
				
				long value = (mid * mid + mid) / 2;
				
				if (value == target) {
					k = mid;
					break;
				} else if (value > target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, k);
		}
	}
}