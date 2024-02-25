import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 0.1 초 1000만 연산 10^7
        // 10^3

        String str1, str2;

        str1 = br.readLine();
        str2 = br.readLine();

        /////////////////////////////////////////////////////////////////////

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {

                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }

            }
        }

        int answer = -1;
        int answerI = -1;
        int answerJ = -1;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] >= answer) {
                    answer = dp[i][j];
                    answerI = i;
                    answerJ = j;
                }
            }
        }

        System.out.println(answer);
        String answerStr = "";

        while (true) {

            if (dp[answerI][answerJ] == 0)
                break;

            if (str1.charAt(answerI-1) == str2.charAt(answerJ-1)) {
                answerStr = str1.charAt(answerI-1) + answerStr;
                answerI--;
                answerJ--;
            } else {
                if (dp[answerI - 1][answerJ] > dp[answerI][answerJ - 1]) {
                    answerI--;
                } else {
                    answerJ--;
                }
            }
        }

        System.out.println(answerStr);


    }
}