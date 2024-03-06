import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        boolean[][] wall;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wall = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < M; j++) {
                if (inputLine.charAt(j) == '1') {
                    wall[i][j] = true;
                }
            }
        }

        ////////////////////////////////////////////

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        ArrayList<Integer> counts = new ArrayList<>();
        counts.add(0);

        int islandNumber = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!wall[i][j] && !visited[i][j]) {
                    count = 0;
                    dfs(wall, map, i, j, visited, islandNumber);
                    counts.add(count);
                    islandNumber++;
                }
            }
        }

//        for(Integer value : counts)
//            System.out.print(value +" ");
//        System.out.println();
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (map[i][j] != 0) {
//                    map[i][j] = counts.get(map[i][j]);
//                }
//            }
//        }
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        int[][] answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wall[i][j]) {

                    boolean[] visited2 = new boolean[counts.size()];

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];
//                        System.out.println("newY = " + newY + " newX = "+ newX);

                        if (newY < 0 || newX < 0 || newY >= wall.length || newX >= wall[0].length)
                            continue;

                        if (map[newY][newX] == 0)
                            continue;

                        if (visited2[map[newY][newX]])
                            continue;

                        visited2[map[newY][newX]] = true;
                        answer[i][j] += counts.get(map[newY][newX]);
//                        System.out.println("map[i][j] = " + map[i][j]);
//                        System.out.println("counts.get(map[i][j]) = " + counts.get(map[i][j]));
//                        System.out.println("answer[i][j] = " + answer[i][j]);
                    }

                    answer[i][j]++;

                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(answer[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(boolean[][] wall, int[][] map, int i, int j, boolean[][] visited, int islandNumber) {
        count++;
        visited[i][j] = true;
        map[i][j] = islandNumber;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newX < 0 || newY >= wall.length || newX >= wall[0].length)
                continue;

            if (wall[newY][newX])
                continue;

            if (visited[newY][newX])
                continue;

            dfs(wall, map, newY, newX, visited, islandNumber);

        }

    }
}