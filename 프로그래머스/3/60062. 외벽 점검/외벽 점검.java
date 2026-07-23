import java.util.*;
import java.util.stream.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int weakLen;
    
    public int solution(int n, int[] weak, int[] dist) {
        weakLen = weak.length;
        // System.out.println(weakLen);
        
        int[] newWeak = new int[weakLen * 2];
        for(int i = 0; i < weakLen; i++) {
            newWeak[i] = weak[i];
        }
        for(int i = weakLen; i < weakLen * 2; i++) {
            newWeak[i] = weak[(i - weakLen)] + n;
        }
        weak = newWeak;
        // System.out.println(Arrays.toString(weak));
        
        // 1. 모든 weak 출발 지점부터
        for(int startIndex = 0; startIndex < weakLen; startIndex++) {
            // 2. 모든 dist 순서 경우의 수 구하고 순서대로 점검
            permutation(dist, startIndex, weak, new int[dist.length], 0, new boolean[dist.length]);
        }
        
        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
    
    static void permutation(int[] dist, int startIndex, int[] weak, int[] output, int depth, boolean[] visited) {
        if (depth == dist.length) {
            // 3. 몇명으로 점검 되는지 확인. 작은거 ans
            int ans = calcPersonCount(output, weak, startIndex);
            if (ans != -1) {
                answer = Math.min(answer, ans);
            }
            return;
        }
        
        for(int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            output[depth] = dist[i];
            permutation(dist, startIndex, weak, output, depth + 1, visited);
            output[depth] = 0;
            visited[i] = false;
        }
    }
    
    // 취약점 순서대로 확인하면서, 닿는 곳 확인하기.
    static int calcPersonCount(int[] output, int[] weak, int startIndex) {
        int farDist = -1;
        int outputIndex = 0;
        for(int i = startIndex; i < startIndex + weakLen; i++) {
            if (farDist < weak[i]) {
                if (outputIndex == output.length) return -1;
                farDist = weak[i] + output[outputIndex++];
            }
        }
        return outputIndex;
    }
}