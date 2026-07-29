import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Long> queue = new PriorityQueue<>((s1, s2) -> Long.compare(s1, s2));
        // queue.addAll(scoville);
        for(long i: scoville) {
            queue.offer(i);
        }
        
        int count;
        for(count = 0; !queue.isEmpty() && queue.peek() < K; count++) {
            long a = queue.poll();
            if (queue.isEmpty()) return -1;
            long b = queue.poll();
            queue.offer(scramble(a, b));
        }
        
        if (queue.isEmpty()) return -1;
        return count;
    }
    
    long scramble(long a, long b) {
        if (a > b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        return a + (b * 2);
    }
}
// N Log N = 1,000,000 * 20 = 20,000,000