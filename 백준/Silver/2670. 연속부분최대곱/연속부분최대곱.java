import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        double[] arr;

        N = Integer.parseInt(br.readLine());

        arr = new double[N];
        for(int i =0 ; i < N ; i++){
            arr[i] = Double.parseDouble(br.readLine());
        }

        ///////////////////////////////////////////////

        // N^2 을 확인해야 원래 알 수 있다.

        double[] dp = new double[N];
        dp[0] = arr[0];

        for(int i = 1 ; i < N ; i++){
            dp[i] = Math.max(dp[i-1]*arr[i],arr[i]);
        }

        double answer = -1;
        for(int i =0 ; i < N ; i++){
            answer = Math.max(answer,dp[i]);
        }

        System.out.printf("%.3f",answer);



    }
}