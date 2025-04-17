import java.util.*;

class Pos {
    int y, x;
    Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    Pos(Pos o) {
        this.y = o.y;
        this.x = o.x;
    }
}

class Solution {    
    public int solution(int[][] points, int[][] routes) {
        List<List<Pos>> shortRoutes = new ArrayList<>();
        Pos[] pointPos = new Pos[points.length + 1];
        for(int i = 0; i < points.length; i++) {
            pointPos[i + 1] = new Pos(points[i][0], points[i][1]);
        }
        for(int[] route: routes) {
            List<Pos> shortRoute = new ArrayList<>();
            shortRoute.add(new Pos(pointPos[route[0]]));
            for(int i = 0; i < route.length - 1; i++) {
                List<Pos> posList = calcShortPos(pointPos[route[i]], pointPos[route[i + 1]]);
                shortRoute.addAll(posList);
            }
            shortRoutes.add(shortRoute);
        }
        
        int maxLen = 0;
        int pp = 0;
        for(List<Pos> s: shortRoutes) {
            maxLen = Math.max(maxLen, s.size());
        }
        
        int answer = 0;
        for(int t = 0; t < maxLen; t++) {
            Map<String, Integer> map = new HashMap<>();
            for(List<Pos> s: shortRoutes) {
                if (t >= s.size()) {
                    continue;
                }
                Pos k = s.get(t);
                String value = k.y + "," + k.x;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for(int c: map.values()) {
                if (c > 1) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    List<Pos> calcShortPos(Pos start, Pos end) {
        List<Pos> posList = new ArrayList<>();
        Pos cur = new Pos(start);
        while(cur.y != end.y || cur.x != end.x) {
            if (cur.y < end.y) {
                cur.y++;
            } else if (cur.y > end.y) {
                cur.y--;
            } else {
                if (cur.x < end.x) {
                    cur.x++;
                } else if (cur.x > end.x) {
                    cur.x--;
                }
            }
            posList.add(new Pos(cur));
        }
        return posList;
    }
}
