import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] points = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        ////////////////////////////////////////////////////

        int answer = 0;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {

                int minX = Math.min(points[i][0], points[j][0]);
                int minY = Math.min(points[i][1], points[j][1]);

                int maxX = minX + L;
                int maxY = minY + L;

                int count = 0;

                for (int k = 0; k < K; k++) {
                    if (points[k][0] >= minX && points[k][0] <= maxX && points[k][1] >= minY && points[k][1] <= maxY)
                        count++;
                }

                answer = Math.max(answer, count);

            }
        }

        System.out.println(K - answer);

    }
}
