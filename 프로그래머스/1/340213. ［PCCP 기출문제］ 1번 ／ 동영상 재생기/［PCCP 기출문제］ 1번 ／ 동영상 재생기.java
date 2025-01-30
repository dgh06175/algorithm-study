class Solution {
    private int convert(String time) {
        String[] splitTime = time.split(":");
        int min = Integer.valueOf(splitTime[0]);
        int sec = Integer.valueOf(splitTime[1]);
        return min * 60 + sec;
    }
    
    private String convert(int time) {
        int min = time / 60;
        int sec = time % 60;
        return String.format("%02d:%02d", min, sec);
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_sec = convert(video_len);
        int op_start_sec = convert(op_start);
        int pos_sec = convert(pos);
        int op_end_sec = convert(op_end);
        
        if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) {
            pos_sec = op_end_sec;
        }
        
        for (String command: commands) {
            if (command.equals("next")) {
                pos_sec = Math.min(pos_sec + 10, video_len_sec);
            }
            if (command.equals("prev")) {
                pos_sec = Math.max(pos_sec - 10, 0);
            }
            if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) {
                pos_sec = op_end_sec;
            }
        }
        return convert(pos_sec);
    }
}