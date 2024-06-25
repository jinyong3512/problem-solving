import java.util.*;
import java.io.*;

public class Main {

    public static int answer1 = 0;
    public static int answer2 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;

        n = Integer.parseInt(br.readLine());

        fib(n);
        fibonacci(n);

        System.out.println(answer1+" "+answer2);

    }

    public static int fib(int n) {

        if (n == 1 || n == 2) {
            answer1++;
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static int fibonacci(int n) {
        int[] f = new int[n+1];
        f[1] = 1;
        f[2] = 1;

        for(int i = 3 ; i <= n ; i++){
            answer2++;
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }
}