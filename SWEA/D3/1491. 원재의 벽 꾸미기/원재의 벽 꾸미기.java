import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken()); // r * c <= n
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			long ans = Long.MAX_VALUE;
			
			// c <= n / r
			for(long r = 1; r * r <= n; r++) {
				long c1 = r;
				long c2 = n / r;
				
				long value1 = a * Math.abs(r - c1) + b * (n - r * c1);
				ans = Math.min(ans, value1);
				
				long value2 = a * Math.abs(r - c2) + b * (n - r * c2);
				ans = Math.min(ans, value2);
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}