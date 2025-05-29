import java.util.BitSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int truthPersonCount = sc.nextInt();
        BitSet truth = new BitSet();
        for (int i = 0; i < truthPersonCount; i++) {
            truth.set(sc.nextInt());
        }

        BitSet party[] = new BitSet[m];
        for (int i = 0; i < m; i++) {
            int cnt = sc.nextInt();
            party[i] = new BitSet();
            for (int j = 0; j < cnt; j++) {
                party[i].set(sc.nextInt());
            }
        }

        // 전체 돌면서 진실을 아는 사람과 함께 했던 사람들 모두 진실 아는것으로 변경
        for (int i = 0; i < m; i++) {
            BitSet tmp = (BitSet) party[i].clone();
            tmp.and(truth);
            if (!tmp.isEmpty()) {
                truth.or(party[i]);
            }
        }

        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < m; i++) {
                BitSet tmp = (BitSet) party[i].clone();
                tmp.and(truth);
                if (!tmp.isEmpty()) {
                    BitSet before = (BitSet) truth.clone();
                    truth.or(party[i]);
                    if (!truth.equals(before)) {
                        updated = true;
                    }
                }
            }
        } while (updated);

        int ans = 0;
        for (int i = 0; i < m; i++) {
            BitSet tmp = (BitSet) party[i].clone();
            tmp.and(truth);
            if (tmp.isEmpty()) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
