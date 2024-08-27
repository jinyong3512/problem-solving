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

        int[] arr = new int[2 * M];

        for (int i = 0; i < N; i++) {

            arr[0] += 0;
            arr[grows[i][0]] -= 0;

            arr[grows[i][0]] += 1;
            arr[grows[i][0] + grows[i][1]] -= 1;

            arr[grows[i][0] + grows[i][1]] += 2;
            arr[2*M-1] -=2;
        }

        int[] result = new int[2 * M - 1];
        result[0] = arr[0] + 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i - 1] + arr[i];
        }

        for (int j = 0; j < M; j++) {
            sb.append(result[result.length / 2 + j]).append(" ");
        }
        sb.append("\n");

        for (int i = 1; i < M; i++) {

            sb.append(result[result.length / 2 - i]).append(" ");

            for (int j = 1; j < M; j++) {
                sb.append(result[result.length / 2 + j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }
}