import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};
        int[] answer = new int[n];
        // 자연수 n개로 이루어진 집합 중 합이 s가 되는 집합중 원소의 곱이 최대인 집합 구하기
        int remain = s;
        for(int i = 0; i < n; i++) {
            answer[i] = s / n;
            remain -= answer[i];
        }
        
        for(int i = 0; i < remain; i++) {
            answer[n - 1 - i]++;
        }
        
        // System.out.println(Arrays.toString(answer));
        
        return answer;
    }
    
    // n <= 10,000
    // s <= 100,000,000
}

// 1. s 부터 1까지 나눠보기 -> 시간초과
// s 나누기 n 을 하고, 나머지를 배분