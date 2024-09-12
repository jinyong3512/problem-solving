import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean[][] traps = new boolean[R][C];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            traps[r][c] = true;
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());

        int[] directions = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            directions[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        ///////////////////////////////////////////////////////

        boolean[][] visited = new boolean[R][C];
        int curY = sr;
        int curX = sc;
        int directionsIndex = 0;

        while (true) {

            visited[curY][curX] = true;

            int i = 0;

            for (; i < 4; i++) {
                int curDirectionsIndex = (directionsIndex + i) % 4;

                int newY = curY + dy[directions[curDirectionsIndex]];
                int newX = curX + dx[directions[curDirectionsIndex]];

                if (newY < 0 || newY >= R || newX < 0 || newX >= C)
                    continue;

                if (visited[newY][newX])
                    continue;

                if (traps[newY][newX])
                    continue;

                curY = newY;
                curX = newX;
                directionsIndex = curDirectionsIndex;

                break;
            }

            if (i == 4)
                break;

        }

        System.out.println(curY + " " + curX);

    }
}