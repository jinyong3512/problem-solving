import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] fees;
        int M;

        N = Integer.parseInt(br.readLine());

        fees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fees[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        ///////////////////////////////////////////////

        int left = 1;
        int right = 1;
        
        for(int fee : fees)
            right = Math.max(right,fee);

        while (left <= right) {
            int mid = (left + right) / 2;

            int sum = 0;
            for (int fee : fees) {
                sum += Math.min(fee, mid);
            }


            if (sum > M) {
                right = mid - 1;
            } else if (sum == M) {
                System.out.println(mid);
                System.exit(0);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);


    }
}
