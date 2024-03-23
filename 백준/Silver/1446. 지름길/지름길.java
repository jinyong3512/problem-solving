import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, D;
        int[][] data;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        data = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            data[i][2] = Integer.parseInt(st.nextToken());
        }

        /////////////////////////////////////////////////////////

        // N은 12 이하인 양의 정수
        // D 는 10000 보다 작거나 같은 자연수
        // 모든 위치와 길이는 10000보다 작거나 같은 음이 아닌 정수이다.

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] == o2[1])
                    return 0;
                else
                    return -1;
            }
        });


        int[] dp = new int[10001];
        for (int i = 1; i <= 10000; i++) {
            dp[i] = i;
        }

        for (int j = 0; j < data.length; j++) {
            int start = data[j][0];
            int end = data[j][1];
            int distance = data[j][2];

            dp[end] = Math.min(dp[end], dp[start] + distance);

            for (int i = end + 1; i <= 10000; i++) {
                dp[i] = Math.min(dp[i], dp[end] + (i - end));
            }
        }

        System.out.println(dp[D]);


    }
}
