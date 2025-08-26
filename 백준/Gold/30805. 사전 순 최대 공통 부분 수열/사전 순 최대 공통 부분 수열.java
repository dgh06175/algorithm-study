import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> answer = new ArrayList<>();
        int a_index = 0;
        int b_index = 0;

        while (a_index < n && b_index < m) {
            int max_num = -1;
            int a_new_index = a_index;
            int b_new_index = b_index;
            for (int i = a_index; i < n; i++) {
                for (int j = b_index; j < m; j++) {
                    if (A[i] == B[j] && max_num < A[i]) {
                        max_num = A[i];
                        a_new_index = i;
                        b_new_index = j;
                    }
                }
            }
            if (max_num == -1) {
                break;
            }
            answer.add(max_num);
            a_index = a_new_index + 1;
            b_index = b_new_index + 1;
        }

        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            if (i < answer.size() - 1) {
                System.out.print(answer.get(i) + " ");
            } else {
                System.out.print(answer.get(i));
            }
        }
    }
}