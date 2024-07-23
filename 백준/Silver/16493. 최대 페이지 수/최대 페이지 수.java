import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 남은 기간 N | 챕터의 수 M
        // 소요되는 일 수 | 페이지 수

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] datas = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            datas[i][0] = Integer.parseInt(st.nextToken());
            datas[i][1] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////

        int[][] dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            for(int j = 0 ; j <= N ; j++){

                if(j-datas[i][0]>=0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-datas[i][0]] + datas[i][1]);
                }else
                    dp[i][j] = dp[i-1][j];

            }

        }

        System.out.println(dp[M][N]);


    }
}
