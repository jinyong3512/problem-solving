import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        /// /////////////////////////////////////////

        long left = 0;
        long right = Long.MAX_VALUE;

        while (left <= right) {
            long K = (left + right) / 2;

            int count = 0;
            long money = 0;
            boolean pass = false;
            for (int value: arr) {
                if (money > value)
                    money -= value;
                else if (money == value)
                    money -= value;
                else {
                    money = K;
                    count += 1;
                    if (money < value) {
                        left = K + 1;
                        pass = true;
                    }
                    money -= value;
                }
            }
            if (!pass) {
                if (count > M)
                    left = K + 1;
                else if (count == M)
                    right = K - 1;
                else
                    right = K - 1;
            }
        }

        // 8888876544321
        System.out.println(left);


    }
}