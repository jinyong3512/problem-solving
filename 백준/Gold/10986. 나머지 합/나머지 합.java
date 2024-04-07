import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // n^2으로 하면 된다

        // N 1 ~ 10^6
        // M 2 ~ 10^3
        // number 0 ~ 10^9

        int N, M;
        int[] numbers;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////

        long[] sum = new long[N];
        sum[0] = numbers[0];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + numbers[i];
        }

//        for(long tmp : sum)
//            System.out.print(tmp + " ");
//        System.out.println();

        long[] mods = new long[N];
        for (int i = 0; i < N; i++) {
            mods[i] = sum[i] % M;
        }

//        for(long tmp : mods)
//            System.out.print(tmp + " ");
//        System.out.println();

        long[] count = new long[M];

        for (int i = 0; i < N; i++) {
            count[(int) mods[i]]++;
        }

        long answer = 0;

        for (int i = 0; i < M; i++) {
            answer = answer + count[i] * (count[i] - 1) / 2;
        }

        System.out.println(answer+ count[0]);


    }
}
