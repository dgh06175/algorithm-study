class Solution {
    private int convert(String time) {
        String[] splitTime = time.split(":");
        int min = Integer.valueOf(splitTime[0]);
        int sec = Integer.valueOf(splitTime[1]);
        return min * 60 + sec;
    }
    
    private String convert(int time) {
        String min = String.valueOf(time / 60);
        if (min.length() == 1) {
            min = "0" + min;
        }
        String sec = String.valueOf(time % 60);
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        return min + ":" + sec;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_sec = convert(video_len);
        int op_start_sec = convert(op_start);
        int pos_sec = convert(pos);
        int op_end_sec = convert(op_end);
        
        for(String command: commands) {
            if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) {
                pos_sec = op_end_sec;
            }
            if (command.equals("next")) {
                if (pos_sec + 10 >= video_len_sec) {
                    pos_sec = video_len_sec;
                } else {
                    pos_sec += 10;
                }
            }
            if (command.equals("prev")) {
                if (pos_sec - 10 <= 0) {
                    pos_sec = 0;
                } else {
                    pos_sec -= 10;
                }
            }
            if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) {
                pos_sec = op_end_sec;
            }
        }
        return convert(pos_sec);
    }
}