class Solution {
    static final int MAX_TIME = 360000;
    long[] player;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeStrToInt(play_time);
        int advTime = timeStrToInt(adv_time);
        player = new long[playTime + 1];
        
        for(String log: logs) {
            String[] logAry = log.split("-");
            int startTime = timeStrToInt(logAry[0]);
            int endTime = timeStrToInt(logAry[1]);
            player[startTime]++;
            player[endTime]--;
        }
        
        calcPlayerSum(player);
        int ans = getAnswer(player, advTime);
        return timeIntToStr(ans);
    }
    
    int getAnswer(long[] player, int advTime) {
        int maxIndex = 0;
        long count = 0L;
        for(int i = 0; i < advTime; i++) {
            count += player[i];
        }
        long maxCount = count;
        
        for(int i = advTime; i < player.length - 1; i++) {
            count += player[i];
            count -= player[i - advTime];
            if (count > maxCount) {
                maxCount = count;
                maxIndex = i - advTime + 1;
            }
        }
        return maxIndex;
    }
    
    void calcPlayerSum(long[] player) {
        long cur = 0;
        for(int i = 0; i < player.length; i++) {
            cur += player[i];
            player[i] = cur;
        }
    }
    
    private int timeStrToInt(String timeString) {
        String[] ary = timeString.split(":");
        int time = 0;
        time += Integer.parseInt(ary[0]) * 60 * 60;
        time += Integer.parseInt(ary[1]) * 60;
        time += Integer.parseInt(ary[2]);
        return time;
    }
    
    private String timeIntToStr(int time) {
        int h = time / 3600;
        int m = (time - h * 3600) / 60;
        int s = (time - h * 3600) - m * 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}