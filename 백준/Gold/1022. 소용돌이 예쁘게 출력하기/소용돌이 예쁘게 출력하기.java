import java.io.*;
import java.util.*;

public class Main {

    // 우 상 좌 하
    public static int[] dy = new int[]{0, -1, 0, 1};
    public static int[] dx = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////

        int[][] answer = new int[r2 - r1 + 1][c2 - c1 + 1];

        int fullCount = (r2 - r1 + 1) * (c2 - c1 + 1);
        int y = 0;
        int x = 0;
        int fillCount = 0;
        int number = 1;
        int direction = 0;
        int curDepth = 0;
        int fullDepth = 1;

        while (true) {
            if (r1 <= y && y <= r2 && c1 <= x && x <= c2) {
                answer[y - r1][x - c1] = number;
                fillCount++;

                if (fillCount == fullCount)
                    break;
            }

            number++;
            y = y + dy[direction];
            x = x + dx[direction];
            curDepth++;

            if (fullDepth == curDepth) {
                curDepth = 0;
                direction = (direction + 1) % 4;
                if (direction == 2 || direction == 0) {
                    fullDepth++;
                }
            }

        }

        int maxLength = 0;
        for(int i =0 ; i < answer.length ; i++){
            for(int j =0 ; j < answer[0].length ; j++){
                maxLength = Math.max(maxLength,String.valueOf(answer[i][j]).length());
            }
        }

        for(int i = 0 ; i < answer.length; i++){
            for(int j = 0;  j < answer[0].length ; j++){
                int gap = maxLength  - String.valueOf(answer[i][j]).length();
                for(int k = 0 ; k < gap ; k++){
                    sb.append(" ");
                }
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }
}
