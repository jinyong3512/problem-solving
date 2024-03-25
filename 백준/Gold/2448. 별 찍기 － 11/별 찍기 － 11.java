import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

//        N은 항상 3*2^k
//        k는 0 ~ 10

        // 3 6 12 24 48 ....

        // 3 * 1 k = 0
        // N = 3
        // M = 5

        // 3 * 2 k = 1
        // N = 3*2
        // M = 5*2+1

        // 3 * 4 k = 2
        // N = 3*4
        // M = (5*2+1)*2 +1

        // 3 * 8 k = 3
        // N = 3*8
        // M = ((5*2+1)*2 +1)*2 +1

        int N;
        char[][] answer;

        N = Integer.parseInt(br.readLine());

        //////////////////////////////////////

        int k = 0;
        while (true) {
            if (Math.pow(2, k) * 3 == N)
                break;
            k++;
        }

        int M = 5;
        for (int i = 0; i < k; i++) {
            M = M * 2 + 1;
        }

        // 5 11 23 ....

        answer = new char[N][M];

        recursion(answer, k, N - 1, M / 2);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (answer[i][j] == '*')
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void recursion(char[][] answer, int k, int y, int x) {
        if (k == 0) {
            answer[y][x - 2] = '*';
            answer[y][x - 1] = '*';
            answer[y][x] = '*';
            answer[y][x + 1] = '*';
            answer[y][x + 2] = '*';

            answer[y - 1][x - 1] = '*';
            answer[y - 1][x + 1] = '*';

            answer[y - 2][x] = '*';

            return;
        }

        recursion(answer, k - 1, (int) (y - (3 * Math.pow(2,k-1))), x);

        // k가 1이면 -3 3*2^(k-1)
        // k가 2이면 -6 3*2^(k-1)
        // k가 3이면 -12

        recursion(answer, k - 1, y, (int)(x - 3 * Math.pow(2,k-1)));
        recursion(answer, k - 1, y, (int)(x + 3 * Math.pow(2,k-1)));

        // k가 1이면 -3 +3
        // k가 2이면 -6 +6
        // k가 3이면 -12 +12

    }
}
