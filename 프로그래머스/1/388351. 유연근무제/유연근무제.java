class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        for(int i = 0; i < n; i++) {
            boolean isEvent = true;
            for(int d = 0; d < 7; d++) {
                int day = (startday + d - 1) % 7 + 1;
                if (day == 6 || day == 7) {
                    continue;
                }
                if (isLate(schedules[i], timelogs[i][d])) {
                    isEvent = false;
                }
            }
            if (isEvent) {
                answer += 1;
            }
        }
        return answer;
    }
    
    boolean isLate(int schedule, int time) {
        int gap = 0;
        int s_h = schedule / 100;
        int s_m = schedule % 100;
        int t_h = time / 100;
        int t_m = time % 100;
        gap += (t_h - s_h) * 60;
        gap += t_m - s_m;
        return gap > 10;
    }
}