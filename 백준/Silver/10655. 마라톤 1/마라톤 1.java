import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 10만
        int N;

        int[] y;
        int[] x;

        N = Integer.parseInt(br.readLine());

        y = new int[N];
        x = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            y[i] = Integer.parseInt(st.nextToken());
            x[i] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////////////////////////

        int sum = 0;
        for (int i = 1; i < N; i++) {
            sum += Math.abs(y[i] - y[i - 1]) + Math.abs(x[i] - x[i - 1]);
        }

        int answer =  sum;
        for (int i = 1; i < N - 1; i++) {

            int newAnswer = sum;

            newAnswer = newAnswer - Math.abs(y[i] - y[i - 1]) - Math.abs(x[i] - x[i - 1]);
            newAnswer = newAnswer - Math.abs(y[i + 1] - y[i]) - Math.abs(x[i + 1] - x[i]);
            newAnswer = newAnswer + Math.abs(y[i + 1] - y[i - 1]) + Math.abs(x[i + 1] - x[i - 1]);

            answer = Math.min(answer, newAnswer);

        }

        System.out.println(answer);

    }
}