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

            System.out.println(getAllKey(map, keys));
        }
    }

    static int getAllKey(char[][] map, Set<Character> keys) {
        int h = map.length, w = map[0].length;
        int ans = 0;
        boolean[][] visited = new boolean[h][w];
        List<int[]>[] doors = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            doors[i] = new ArrayList<>();
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
                    continue;
                }
                char c = map[ny][nx];
                if (visited[ny][nx] || c == '*') {
                    continue;
                }

                if (c == '$') {
                    ans++;
                } else if ('a' <= c && c <= 'z') {
                    if (keys.add(c)) {
                        for (int[] p : doors[c - 'a']) {
                            queue.offer(p);
                        }
                    }
                } else if ('A' <= c && c <= 'Z') {
                    char lower = Character.toLowerCase(c);
                    if (!keys.contains(lower)) {
                        doors[lower - 'a'].add(new int[] { ny, nx });
                        continue;
                    }
                }
                map[ny][nx] = '.';
                queue.offer(new int[] { ny, nx });
                visited[ny][nx] = true;
            }
        }

        return ans;
    }
}