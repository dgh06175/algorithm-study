import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			long[] ary = new long[n];
			for(int i = 0; i < n; i++) {
				ary[i] = Long.parseLong(st.nextToken());
				
			}
			
			int answer = lis(ary, n);
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	//	lis[k] = 길이가 k+1인 증가 부분 수열들 중, 끝나는 값이 가장 작은 것의 그 마지막 값
	static int lis(long[] ary, int n) {
		long[] lis = new long[n];
		int length = 0;
		lis[0] = ary[0];
		
		for(int i = 1; i < n; i++) {
			if (lis[length] < ary[i]) {
				lis[++length] = ary[i];
			} else {
				int index = binarySearch(lis, length + 1, ary[i]);
				if (index == n) continue;
				lis[index] = ary[i];
			}
		}
		
		return length + 1;
	}
	
	static int binarySearch(long[] ary, int n, long target) {
		int start = 0;
		int end = n - 1;
		int ans = n - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			if (ary[mid] >= target) {
				end = mid - 1;
				ans = mid;
			} else {
				start = mid + 1;
			}
		}
		return ans;
	}
}