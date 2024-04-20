import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ////////////////////////////////////////////

        long[] dp = new long[N + 1];

        if (N % 2 != 0) {
            System.out.println("0");
        } else {
            System.out.println(find(dp, N));
        }

    }

    public static long find(long[] dp, int N) {

        if (N == 0) {
            return 1;
        }

        if (dp[N] != 0) {
            return dp[N];
        }

        dp[N] = find(dp,N-2)*3;
        for(int i = N-4 ; i>=0 ; i-=2){
            dp[N] += find(dp,i)*2;
        }
        return dp[N];

    }
}
