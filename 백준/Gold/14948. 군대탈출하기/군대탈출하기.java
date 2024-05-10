import java.io.*;
import java.util.*;

class Point {
    int y, x, maxHeight;
    int usedChance;

    Point(int y, int x, int maxHeight, int usedChance) {
        this.y = y;
        this.x = x;
        this.maxHeight = maxHeight;
        this.usedChance = usedChance;
    }
}

public class Main {

    public static int answer = Integer.MAX_VALUE;
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 블록의 숫자가 3이라면 최소한 레벨 3이 되어야 통과할 수 있다
        // 단 한번 타일을 무시하고 건너뛰어 다음 탈로 갈 수 있다

        // n m 1 ~ 100
        // k 0 ~ 10^9

        int n, m;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////////

        int[][][] rememberMaxHeight = new int[2][n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rememberMaxHeight[0][i][j] = 1000000000 + 1;
                rememberMaxHeight[1][i][j] = 1000000000 + 1;
            }
        }
        rememberMaxHeight[0][0][0] = arr[0][0];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, arr[0][0], 0));

        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();

            if(curPoint.y == n-1 && curPoint.x == m-1){
                answer = Math.min(answer,curPoint.maxHeight);
                continue;
            }

            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.y + dy[direction];
                int newX = curPoint.x + dx[direction];

                if (newY < 0 || newY >= n || newX < 0 || newX >= m)
                    continue;

                int newMaxHeight = Math.max(curPoint.maxHeight, arr[newY][newX]);

                if (rememberMaxHeight[curPoint.usedChance][newY][newX] <= newMaxHeight)
                    continue;

                rememberMaxHeight[curPoint.usedChance][newY][newX] = newMaxHeight;
                queue.add(new Point(newY, newX, newMaxHeight, curPoint.usedChance));
            }

            if (curPoint.usedChance == 0) {
                for (int direction = 0; direction < 4; direction++) {
                    int newY = curPoint.y + dy[direction] + dy[direction];
                    int newX = curPoint.x + dx[direction] + dx[direction];

                    if (newY < 0 || newY >= n || newX < 0 || newX >= m)
                        continue;

                    int newMaxHeight = Math.max(curPoint.maxHeight, arr[newY][newX]);

                    if (rememberMaxHeight[1][newY][newX] <= newMaxHeight)
                        continue;

                    rememberMaxHeight[1][newY][newX] = newMaxHeight;
                    queue.add(new Point(newY, newX, newMaxHeight, 1));
                }
            }


        }

        System.out.println(answer);


    }
}
