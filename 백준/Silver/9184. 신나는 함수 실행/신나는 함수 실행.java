import java.io.*;
import java.util.*;

public class Main {

    public static int[][][] dp = new int[101][101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // -50 ~ 50

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1)
                break;
            sb
                    .append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
                    .append(w(a, b, c))
                    .append("\n");
        }
        System.out.println(sb);

    }

    public static int w(int a, int b, int c) {

        if (dp[a + 50][b + 50][c + 50] != 0)
            return dp[a + 50][b + 50][c + 50];

        if (a <= 0 || b <= 0 || c <= 0)
            return 1;

        if (a > 20 || b > 20 || c > 20)
            return dp[a + 50][b + 50][c + 50] = w(20, 20, 20);

        if (a < b && b < c)
            return dp[a + 50][b + 50][c + 50] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);

        return dp[a + 50][b + 50][c + 50] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }
}