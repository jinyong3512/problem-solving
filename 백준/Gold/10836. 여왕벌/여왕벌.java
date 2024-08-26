import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] grows = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            grows[i][0] = Integer.parseInt(st.nextToken());
            grows[i][1] = Integer.parseInt(st.nextToken());
            grows[i][2] = Integer.parseInt(st.nextToken());
        }

        /////////////////////////////////////////

        int[][] map = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = 1;
            }
        }

        // 490,000 M*M
        // 10^6 N

        for (int n = 0; n < N; n++) {
            int[] grow = grows[n];

            int[] curGrow = new int[2 * M - 1];
            int curGrowIndex = 0;
            for (int i = 0; i < grow[0]; i++)
                curGrow[curGrowIndex++] = 0;
            for (int i = 0; i < grow[1]; i++)
                curGrow[curGrowIndex++] = 1;
            for (int i = 0; i < grow[2]; i++)
                curGrow[curGrowIndex++] = 2;

            int y = M - 1;
            int x = 0;

            for (int i = 0; i < curGrow.length; i++) {
                map[y][x] += curGrow[i];
                if (y == 0)
                    x++;
                else
                    y--;
            }

            y = 1;
            x = 1;
            for (; x < M; x++) {
                map[y][x] += curGrow[curGrow.length / 2 + x];
            }

        }

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if( y >= 2 && x >= 1){
                    sb.append(map[1][x]).append(" ");
                }
                else
                    sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
