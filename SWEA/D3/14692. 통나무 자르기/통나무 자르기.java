import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			String answer = n % 2 == 0 ? "Alice" : "Bob";
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(answer)
			.append("\n");
		}
		System.out.println(sb.toString());
	}
}
