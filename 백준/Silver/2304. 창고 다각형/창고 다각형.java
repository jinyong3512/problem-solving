import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 1이상 1000이하
        boolean[][] exists = new boolean[1001][1001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            for (int y = 1; y <= H; y++) {
                exists[y][L] = true;
            }

        }

        /////////////////////////////////////////////

        int answer = 0;

        for (int y = 1; y <= 1000; y++) {

            int left = 1;
            int right = 1000;

            while (left <= 1000) {
                if (exists[y][left])
                    break;
                left++;
            }

            while (right >= 1) {
                if (exists[y][right])
                    break;
                right--;
            }

            if (left == 1001)
                continue;
            else if (right == left) {
                answer += 1;
            } else
                answer += (right - left + 1);


        }

        System.out.println(answer);


    }
}