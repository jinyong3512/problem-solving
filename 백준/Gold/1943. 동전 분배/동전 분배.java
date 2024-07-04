import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        //  N가지 종류의 동전을 각각 몇 개씩 주셨을 때, 그 돈을 반으로 나눌 수 있는지 없는지 판단하는 것

        // 동전을 버리는 것은 선택지에 없는 것 같다.

        for (int t = 0; t < 3; t++) {

            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][100001];

            for (int value = 0; value < 100001; value++)
                dp[0][value] = -1;
            dp[0][0] = 0;

            for (int i = 1; i <= N; i++) {

                int coinValue = arr[i][0];
                int coinNumber = arr[i][1];

                for (int value = 0; value < 100001; value++) {
                    if (dp[i - 1][value] == -1)
                        dp[i][value] = -1;
                    else
                        dp[i][value] = 0;
                }

                for (int value = 0; value < 100001; value++) {

                    // dp[i][value]를 정의하자
                    if(dp[i][value] == 0)
                        continue;

                    if (value - coinValue < 0)
                        continue;

                    // 이전 가격을 만들 수 없다면 패스
                    if (dp[i][value - coinValue] == -1)
                        continue;

                    // 갯수를 다썻다면 패스
                    if (dp[i][value - coinValue] == coinNumber)
                        continue;

                    dp[i][value] = dp[i][value - coinValue] + 1;


                }

//                for(int value = 0 ; value < 100001 ; value++){
//                    if(dp[i][value] != -1)
//                        System.out.println(" i = " + i +" value = " + value +" dp[i][value] = " + dp[i][value]);
//                }
//                System.out.println();
            }

            int sum = 0;
            for(int i = 1; i <= N ; i++)
                sum = sum + arr[i][0] * arr[i][1];

            if(sum%2 ==1)
                sb.append("0").append("\n");
            else{
                if(dp[N][sum/2] == -1)
                    sb.append("0").append("\n");
                else
                    sb.append("1").append("\n");
            }
        }
        System.out.println(sb);

    }
}