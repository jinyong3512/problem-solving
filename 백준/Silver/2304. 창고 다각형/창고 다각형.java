import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 10:38

        boolean[][] visited = new boolean[1000][1000];

        int N;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken());

            for (int h = 0; h < y; h++) {
                visited[h][x] = true;
            }
        }

        int answer = 0;

        for (int i = 0; i < 1000; i++) {
            int leftIndex = 0;
            int rightIndex = 999;

            for (; leftIndex < 1000; leftIndex++) {
                if (visited[i][leftIndex])
                    break;
            }

            for (; rightIndex >= 0; rightIndex--) {
                if (visited[i][rightIndex])
                    break;
            }

            if(leftIndex > rightIndex)
                continue;

            answer += (rightIndex - leftIndex +1);
        }

        System.out.println(answer);


    }
}
