import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int[] dy = { 1, -1, 0, 0 };
    final static int[] dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        while (test_case-- > 0) {
            String[] inputSize = br.readLine().split(" ");
            int h = Integer.parseInt(inputSize[0]);
            int w = Integer.parseInt(inputSize[1]);
            char[][] map = new char[h + 2][w + 2];

            for (int i = 0; i < map.length; i++) {
                Arrays.fill(map[i], '.');
            }

            for (int i = 0; i < h; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    map[i + 1][j + 1] = input[j];
                }
            }

            Set<Character> keys = new HashSet<>();
            char[] inputKeys = br.readLine().toCharArray();
            for (int i = 0; i < inputKeys.length; i++) {
                if (inputKeys[i] == '0') {
                    break;
                }
                keys.add(inputKeys[i]);
            }

            int ans = getAllKey(map, keys);
            System.out.println(ans);
        }
    }

    static int getAllKey(char[][] map, Set<Character> keys) {
        int ans = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        Map<Character, List<int[]>> doorPos = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map[0].length) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                char c = map[ny][nx];
                if (c == '*') {
                    continue;
                }

                if (c == '.' && !visited[ny][nx]) {
                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }

                if ('A' <= c && c <= 'Z') {
                    char doorKey = Character.toLowerCase(c);
                    if (keys.contains(doorKey)) {
                        queue.offer(new int[] { ny, nx });
                        visited[ny][nx] = true;
                    } else {
                        if (doorPos.containsKey(doorKey)) {
                            doorPos.get(doorKey).add(new int[] { ny, nx });
                        } else {
                            List<int[]> list = new ArrayList<>();
                            list.add(new int[] { ny, nx });
                            doorPos.put(doorKey, list);
                        }
                    }
                }
                if ('a' <= c && c <= 'z') {
                    keys.add(c);
                    if (doorPos.containsKey(c)) {
                        for (int[] pos : doorPos.get(c)) {
                            queue.offer(pos);
                            visited[pos[0]][pos[1]] = true;
                        }
                    }
                    map[ny][nx] = '.';
                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
                if (c == '$') {
                    map[ny][nx] = '.';
                    ans += 1;
                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }

        return ans;
    }
}