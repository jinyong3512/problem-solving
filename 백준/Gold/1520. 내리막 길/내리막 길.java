import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 19:55
        // 항상 낮은 높이로만 간다
        // 경로의 갯수를 나타내라

        // M과 N은 500이하의 자연수 , 각 지점의 높이는 10000이하의 자연수

        int N, M;
        int[][] heights;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////////

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }

        recursion(0, 0, dp, heights);

        System.out.println(dp[0][0]);

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    public static int recursion(int curY, int curX, int[][] dp, int[][] heights) {

        if (dp[curY][curX] != -1) {
            return dp[curY][curX];
        }

        if (curY == dp.length - 1 && curX == dp[0].length - 1) {
            dp[curY][curX] = 1;
            return dp[curY][curX];
        }

        int sum = 0;
        for (int direction = 0; direction < 4; direction++) {

            int newY = curY + dy[direction];
            int newX = curX + dx[direction];

            if (newY < 0 || newY >= dp.length || newX < 0 || newX >= dp[0].length)
                continue;

            if (heights[newY][newX] >= heights[curY][curX])
                continue;

            sum += recursion(newY, newX, dp, heights);
        }

        dp[curY][curX] = sum;

        return dp[curY][curX];

    }
}
