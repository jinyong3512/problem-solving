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

        for(int i =0 ; i < N ; i++)
            arr[i] = Integer.parseInt(br.readLine());
        
        // LIS

        // i번째를 무조건 선택 했을때 최장 길이
        int[] dp = new int[N];

        for(int i =0 ; i < N ; i++){
            dp[i] = 1;

            for(int j = i-1 ; j >=0 ; j--){
                if(dp[j]+1 > dp[i] && arr[i] > arr[j])
                    dp[i] = dp[j]+1;
            }
        }

        int answer = 0;
        for(int i =0 ; i < N ; i++){
            answer = Math.max(answer, dp[i]);
        }

        answer = N - answer;

        System.out.println(answer);



    }
}