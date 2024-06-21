import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 떨어진 수열 중 초과하는 최장 수열 길이

        int n;
        int[] arr;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////////////

        int[] dp = new int[n];

        for(int i =0 ; i < n ; i++){
            dp[i] = 1;

            for(int j = 0 ; j < i ; j++){
                if(arr[j] < arr[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int answer = 0;
        for(int i =0 ; i < n ; i++){
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);

    }
}