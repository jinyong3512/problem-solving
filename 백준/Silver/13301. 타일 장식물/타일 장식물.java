import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        /*
         * 1
         * 1
         * 2
         * 3
         * 5 -> 26
         * 8
         *
         * */

        int N;
        long[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];
        arr[0] = 0;
        arr[1] = 1;
//        arr[2] = 1;
//        arr[3] = 2;
//        arr[4] = 3;
//        arr[5] = 5;

        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        System.out.println((arr[N] + arr[N] + arr[N - 1]) * 2);

        // answer = (arr[i] + arr[i-1])*2
    }
}