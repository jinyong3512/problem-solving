import java.io.*;
import java.util.*;

public class Main {

    // 상 우 하 좌
    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int[] dx = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        int i = N / 2;
        int j = N / 2;

        int number = 1;
        arr[i][j] = number;

        int depth = 1;

        while (true) {

            // 상 우

            for (int direction = 0; direction < 2; direction++) {
                for (int d = 1; d <= depth; d++) {
                    i = i + dy[direction];
                    j = j + dx[direction];
                    number++;
                    arr[i][j] = number;

                    if (i == 0 && j == 0)
                        break;

                }

                if (i == 0 && j == 0)
                    break;
            }

            if (i == 0 && j == 0)
                break;

            depth++;

            // 하 좌

            for (int direction = 2; direction < 4; direction++) {
                for (int d = 1; d <= depth; d++) {
                    i = i + dy[direction];
                    j = j + dx[direction];
                    number++;
                    arr[i][j] = number;
                }
            }
            depth++;


        }


        int[] answer = new int[2];

        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < N ; x++){
                sb.append(arr[y][x]).append(" ");

                if(arr[y][x] == K){
                    answer[0] = y+1;
                    answer[1] = x+1;
                }

            }
            sb.append("\n");
        }

        sb.append(answer[0]).append(" ").append(answer[1]).append("\n");

        System.out.println(sb);


    }
}