import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        // i의 입장에서는 i-2,i-1 이런 친구들이 올 수 있는거야
        // i의 입장에서는 i+1, i+2, i+3으로 점프 할 수 있는거지
        int[] dp = new int[N];
        for(int i =0 ; i < N ; i ++){
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for(int i = 0 ; i < N ; i++){
            if(dp[i] == Integer.MAX_VALUE){
                dp[i] = -1;
                continue;
            }

            for(int j = 1; j <= arr[i] ; j++){
                if (i + j < N)
                    dp[i+j] = Math.min(dp[i+j],dp[i]+1);
            }
        }

        System.out.println(dp[N-1]);


    }
}