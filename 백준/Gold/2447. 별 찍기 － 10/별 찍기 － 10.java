import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N이 3의 거듭제곱 형태
        // NxN 형태

        int N = Integer.parseInt(br.readLine());

        ////////////////////////////////////////

        char[][] arr = new char[N][N];
        recursion(N, 0, 0, arr);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void recursion(int N, int y, int x, char[][] arr) {

        if (N == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[y + i][x + j] = '*';
                }
            }
            arr[y + 1][x + 1] = ' ';
            return;
        }

        for (int i = 0; i < N / 3; i++) {
            for (int j = 0; j < N / 3; j++) {
                arr[y + N / 3 + i][x + N / 3 + j] = ' ';
            }
        }

        recursion(N / 3, y, x, arr);
        recursion(N / 3, y + N / 3, x, arr);
        recursion(N / 3, y + N / 3 * 2, x, arr);

        recursion(N / 3, y, x + N / 3, arr);

        recursion(N / 3, y + N / 3 * 2, x + N / 3, arr);

        recursion(N / 3, y, x + N / 3 * 2, arr);
        recursion(N / 3, y + N / 3, x + N / 3 * 2, arr);
        recursion(N / 3, y + N / 3 * 2, x + N / 3 * 2, arr);


    }
}
