import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 산성과 알칼리성
        // 산성 1 ~ 10억 : 10^9
        // 알칼리성 -1 ~ -10억

        // N : 2이상 10^5 이하

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////////////////////

        int minGap = Integer.MAX_VALUE;
        int minGapLeftValue = Integer.MIN_VALUE;
        int minGapRightValue = Integer.MIN_VALUE;
        int leftIndex = 0;
        int rightIndex = N - 1;

        while (leftIndex < rightIndex) {
            int tmpGap = arr[leftIndex] + arr[rightIndex];

            if (minGap > Math.abs(tmpGap)) {
                minGap = Math.abs(tmpGap);
                minGapLeftValue = arr[leftIndex];
                minGapRightValue = arr[rightIndex];
            }

            // 원래는 left를 ++ 해야 함
            if (tmpGap < 0) {
                if (arr[leftIndex] < 0) {
                    leftIndex++;
                } else if (arr[leftIndex] == 0) {
                    rightIndex--;
                } else {
                    rightIndex--;
                }
            } else if (tmpGap == 0) {
                break;
            } else {
                if (arr[rightIndex] < 0) {
                    leftIndex++;
                } else if (arr[rightIndex] == 0) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }
            }
        }

        System.out.println(minGapLeftValue + " " + minGapRightValue);

    }
}