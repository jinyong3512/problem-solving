import java.io.*;
import java.util.*;

class Data {
    int y, x, depth;

    Data(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // # 벽
        // . 지나갈 수 있는 공간
        // J 초기 위치
        // F 불이 난 공간
        // J는 하나만

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        /////////////////////////////////////////

        int[][] timeTable = calculateTimeTable(arr, R, C);

        int answer = getOut(arr, R, C, timeTable);

        if (answer == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(answer);


    }

    public static int[][] calculateTimeTable(char[][] arr, int R, int C) {

        int[][] timeTable = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                timeTable[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Data> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'F') {
                    queue.add(new Data(i, j, 0));
                    timeTable[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            Data curData = queue.remove();

            for (int direction = 0; direction < 4; direction++) {
                int newY = curData.y + dy[direction];
                int newX = curData.x + dx[direction];

                if (newY < 0 || newY >= R || newX < 0 || newX >= C)
                    continue;

                if (arr[newY][newX] == '#')
                    continue;

                if (timeTable[newY][newX] != Integer.MAX_VALUE)
                    continue;

                timeTable[newY][newX] = curData.depth + 1;

                queue.add(new Data(newY, newX, curData.depth + 1));

            }

        }

        return timeTable;

    }

    public static int getOut(char[][] arr, int R, int C, int[][] timeTable) {

        boolean[][] visited = new boolean[R][C];
        Queue<Data> queue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'J') {
                    queue.add(new Data(i, j, 0));
                    visited[i][j] = true;

                    if (i == 0 || i == R - 1 || j == 0 || j == C - 1)
                        return 1;
                }
            }
        }


        while (!queue.isEmpty()) {
            Data curData = queue.remove();

            for (int direction = 0; direction < 4; direction++) {
                int newY = curData.y + dy[direction];
                int newX = curData.x + dx[direction];

                if (arr[newY][newX] == '#')
                    continue;

                if (timeTable[newY][newX] <= curData.depth + 1)
                    continue;

                if (newY == 0 || newY == R - 1 || newX == 0 || newX == C - 1)
                    return curData.depth + 1 + 1;

                if (visited[newY][newX])
                    continue;

                visited[newY][newX] = true;
                queue.add(new Data(newY, newX, curData.depth + 1));

            }

        }

        return Integer.MAX_VALUE;

    }

}
