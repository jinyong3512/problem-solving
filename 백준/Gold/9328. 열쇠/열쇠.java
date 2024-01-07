import java.io.*;
import java.util.*;

public class Main {

    public static int answer;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            answer = 0;

            int h, w;
            char[][] map;
            boolean[] keys = new boolean[26];

            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = '.';
                }
            }

            for (int i = 1; i <= h; i++) {
                String inputLine = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = inputLine.charAt(j - 1);
                }
            }

            String inputLine = br.readLine();

            if (inputLine.charAt(0) == '0') {
            } else {
                for (int i = 0; i < inputLine.length(); i++) {
                    keys[inputLine.charAt(i) - 'a'] = true;
                }
            }

            ////////////////////////////////////////////////////////

            ArrayList<Boolean[]>[][] visited = new ArrayList[map.length][map[0].length];
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    visited[y][x] = new ArrayList<>();
                }
            }

            dfs(map, 0, 0, keys, visited);


            sb.append(answer).append("\n");

        }

        System.out.print(sb);


    }

    public static void dfs(char[][] map, int i, int j, boolean[] keys, ArrayList<Boolean[]>[][] visited) {

        if (map[i][j] == '.') {

        } else if (map[i][j] == '$') {
            answer++;
            map[i][j] = '.';
        } else if ('a' <= map[i][j] && map[i][j] <= 'z') {
            keys[map[i][j] - 'a'] = true;
        } else if ('A' <= map[i][j] && map[i][j] <= 'Z') {
            if (!keys[map[i][j] - 'A'])
                return;
        } else if (map[i][j] == '*') {
            System.out.println(" 별에 접근 했어 버그 임");
            return;
        } else {
            System.out.println(" map[i][j] 버그");
            return;
        }

        Boolean[] newKeys = new Boolean[keys.length];
        for (int index = 0; index < keys.length; index++) {
            newKeys[index] = keys[index];
        }
        visited[i][j].add(newKeys);

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                continue;

            if (map[newY][newX] == '*')
                continue;

            boolean bigSame = false;

            for (int a = 0; a < visited[newY][newX].size(); a++) {
                boolean same = true;
                for (int b = 0; b < visited[newY][newX].get(a).length; b++) {
                    if (keys[b] != visited[newY][newX].get(a)[b])
                        same = false;
                }

                if (same) {
                    bigSame = true;
                }
            }

            if (!bigSame)
                dfs(map, newY, newX, keys, visited);

        }

    }
}