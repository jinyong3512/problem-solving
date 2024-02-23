import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 19:58

        int R, C, N;
        int[][] map;
        int[][] newMap;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < C; j++) {
                if (inputLine.charAt(j) == 'O') {
                    map[i][j] = 3;
                } else {
                    map[i][j] = -1;
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1)
                    map[i][j]--;
            }
        }


        for (int time = 0; time < N - 1; time++) {
            if (time % 2 == 0) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] != -1)
                            map[i][j]--;
                    }
                }
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] == -1)
                            map[i][j] = 3;
                    }
                }

            } else {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] != -1)
                            map[i][j]--;
                    }
                }
                newMap = new int[map.length][map[0].length];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        newMap[i][j] = map[i][j];
                    }
                }

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        if (map[i][j] == 0) {
                            newMap[i][j] = -1;
                            for (int direction = 0; direction < 4; direction++) {
                                int newY = i + dy[direction];
                                int newX = j + dx[direction];

                                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                                    continue;

                                newMap[newY][newX] = -1;

                            }
                        }
                    }
                }
                map = newMap;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == -1) {
                    System.out.print(".");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }

    }
}