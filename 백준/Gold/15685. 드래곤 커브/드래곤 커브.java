import java.io.*;
import java.util.*;

public class Main {

    public static int[] dx = new int[]{1, 0, -1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        /*
        0: x좌표가 증가하는 방향 (→)
        1: y좌표가 감소하는 방향 (↑)
        2: x좌표가 감소하는 방향 (←)
        3: y좌표가 증가하는 방향 (↓)
        */

        boolean[][] board = new boolean[101][101];

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            solve(board, x, y, d, g);
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1])
                    answer++;
            }
        }
        System.out.println(answer);

    }

    public static void solve(boolean[][] board, int x, int y, int d, int g) {

        board[x][y] = true;
        ArrayList<Integer> directions = new ArrayList<>();

        // g == 0
        directions.add(d);
        x = x + dx[d];
        y = y + dy[d];
        board[x][y] = true;

        for (int gIndex = 1; gIndex <= g; gIndex++) {

            int directionsIndex = directions.size() - 1;

            for (; directionsIndex >= 0; directionsIndex--) {
                int curDirection = directions.get(directionsIndex);
                int nextDirection = (curDirection + 1) % 4;

                directions.add(nextDirection);

                x = x + dx[nextDirection];
                y = y + dy[nextDirection];

                board[x][y] = true;
            }

        }
    }
}
