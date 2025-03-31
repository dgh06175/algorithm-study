import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] ary = new String[n];
        for (int i = 0; i < n; i++) {
            ary[i] = br.readLine();
        }

        char[] ans = new char[ary[0].length()];

        for (int i = 0; i < ary[0].length(); i++) {
            ans[i] = ary[0].charAt(i);
            for (int j = 1; j < n; j++) {
                if (ans[i] != ary[j].charAt(i)) {
                    ans[i] = '?';
                    break;
                }
            }
        }

        for (char c : ans) {
            System.out.print(c);
        }
        System.out.println();
    }
}
