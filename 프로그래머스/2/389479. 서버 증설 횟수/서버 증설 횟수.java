class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int n = players.length;
        int[] serverCount = new int[n];

        // players[i] / m > serverCount 면 서버 증설
        for(int i = 0; i < n; i++) {
            if (players[i] / m > serverCount[i]) {
                int addServerCount = players[i] / m - serverCount[i];
                answer += addServerCount;
                for(int j = i; j < i + k && j < n; j++) {
                    serverCount[j] += addServerCount;
                }
            }
        }
        
        return answer;
    }
}