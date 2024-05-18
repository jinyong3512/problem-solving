import java.io.*;
import java.util.*;

public class Main {

    public static int maxDepth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());
            arr[i][0] = number1;
            arr[i][1] = number2;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                return o1[0] - o2[0];

            }
        });

        // i번째를 무조건 선택했을때 갯수
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {

            int curHeight = arr[i][1];
            dp[i] = 1;

            for (int j = i - 1; j >= 0; j--) {

                if(curHeight > arr[j][1] && dp[i] < dp[j]+1)
                    dp[i] = dp[j]+1;

            }
        }

        int answer = 0;

        for(int i =0 ; i < N ; i++)
            answer = Math.max(answer,dp[i]);

        System.out.println(N-answer);


    }
}