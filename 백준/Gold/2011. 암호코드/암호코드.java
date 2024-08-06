import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String N = br.readLine();

        long[] dp = new long[N.length() + 1];

        boolean can = true;

        for (int i = 1; i < N.length() + 1; i++) {

            if (i == 1) {
                if (N.charAt(0) == '0')
                    can = false;
                dp[1] = 1;
            } else {

                // 한자리
                if (N.charAt(i - 1) != '0') {
                    dp[i] += dp[i - 1];
                }

                // 두자리
                if (N.charAt(i - 2) != '0') {
                    String tmp = "";
                    tmp += N.charAt(i - 2);
                    tmp += N.charAt(i - 1);
                    int curNum = Integer.parseInt(tmp);
                    if (1 <= curNum && curNum <= 26) {
                        if (i == 2)
                            dp[i] += 1;
                        else
                            dp[i] = (dp[i] + dp[i - 2]) % 1000000;
                    }
                }

                if (dp[i] == 0)
                    can = false;

            }

        }

//        for(int i = 1 ; i  <= N.length() ; i++)
//            System.out.print(dp[i]+" ");
//        System.out.println();

        if (can)
            System.out.println(dp[N.length()]);
        else
            System.out.println(0);


    }
}
