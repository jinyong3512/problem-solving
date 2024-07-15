import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 정수 X의 각 자리가 등차수열을 이루면 한수

        // 1 ~ 1000
        // 1 ~ N 한수의 갯수

        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1; i <= N; i++) {

            String number = String.valueOf(i);

            boolean can = true;
            for (int index = 2; index < number.length(); index++) {

                if( number.charAt(index) - number.charAt(index-1) != number.charAt(index-1) - number.charAt(index-2)){
                    can = false;
                }

            }

            if(can)
                answer++;
        }

        System.out.println(answer);


    }
}
