import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int t = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] boxes = new int[100];
			for(int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			
			while(t-->0) {
				int maxIndex = findMaxIndex(boxes);
				int minIndex = findMinIndex(boxes);
				boxes[maxIndex] -= 1;
				boxes[minIndex] += 1;
			}
			int maxIndex = findMaxIndex(boxes);
			int minIndex = findMinIndex(boxes);
			int ans = boxes[maxIndex] - boxes[minIndex];
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
	
	static int findMaxIndex(int[] ary) {
		int maxIndex = -1;
		int maxN = Integer.MIN_VALUE;
		for(int i = 0; i < ary.length; i++) {
			if (maxN < ary[i]) {
				maxN = ary[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	static int findMinIndex(int[] ary) {
		int minIndex = -1;
		int minN = Integer.MAX_VALUE;
		for(int i = 0; i < ary.length; i++) {
			if (minN > ary[i]) {
				minN = ary[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}