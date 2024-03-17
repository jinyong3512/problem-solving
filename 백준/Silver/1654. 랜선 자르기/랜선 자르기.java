import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N개의 랜선 만들어야 해

        // K 개의 랜선 길이 제각각

        // 모두 N개의 같은 길이 랜선 만들고 싶어

        // N개 보다 많이 만드는 것도 N개를 만드는 것이다

        // 최대 랜선 길이를 구해라

        // K 1 ~ 10000
        // N 1 ~ 1,000,000

        int K, N;
        long[] ks;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ks = new long[K];

        //////////////////////////////////////////////////

        long right = 0;
        for (int i = 0; i < K; i++) {
            ks[i] = Long.parseLong(br.readLine());
            if (ks[i] > right) {
                right = ks[i]; // 랜선 길이 중 최대값으로 right 초기화
            }
        }

        long left = 1;

        while (left <= right) {
            long curLength = (left + right) / 2;

            int count = 0;
            for (int i = 0; i < ks.length; i++) {
                count = (int) (count + (ks[i] / curLength));
            }

            if (count > N) {
                left = (curLength + 1);
            } else if (count == N) {
                left = (curLength + 1);
            } else {
                right = (curLength - 1);
            }

        }

//        System.out.println("left = " + left);
//        System.out.println("rigth = " + right);

        System.out.println(right);
    }
}