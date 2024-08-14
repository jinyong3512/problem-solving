import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long n = Long.parseLong(br.readLine());

        long[][] a = {{1, 1}, {1, 0}};
        long[][] result = {{1, 0}, {0, 1}};

        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            n /= 2;
        }

        System.out.println(result[0][1]);

    }

    // 행렬 곱셈 함수
    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        c[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % 1000000;
        c[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % 1000000;
        c[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % 1000000;
        c[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % 1000000;
        return c;
    }
}
