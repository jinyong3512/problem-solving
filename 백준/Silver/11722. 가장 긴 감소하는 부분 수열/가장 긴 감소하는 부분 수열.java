import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /////////////////////////////////////////////

        int[] dp = new int[N];

        for(int i =0 ; i < N ; i ++){
            dp[i] = 1;

            for(int j =0  ; j < i ; j++){
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        int answer = 1;
        for(int i =0 ; i < N ; i++){
            answer = Math.max(answer,dp[i]);
        }
        System.out.println(answer);


    }
}