import java.util.*;
import java.io.*;

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

        int[] dp1 = new int[N];
        dp1[0] = 1;

        for(int i = 1 ; i < N ; i++){
            if(arr[i] >= arr[i-1]){
                dp1[i] = dp1[i-1]+1;
            }else{
                dp1[i] = 1;
            }
        }

        int[] dp2 = new int[N];
        dp2[0] = 1;

        for(int i = 1 ; i < N ; i++){
            if(arr[i] <= arr[i-1]){
                dp2[i] = dp2[i-1]+1;
            }else{
                dp2[i] = 1;
            }
        }

        int answer = 0;
        for(int i = 0 ; i < N ; i++){
            answer = Math.max(answer,dp1[i]);
            answer = Math.max(answer,dp2[i]);
        }
        System.out.println(answer);

    }
}