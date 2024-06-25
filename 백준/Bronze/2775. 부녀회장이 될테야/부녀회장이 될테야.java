import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // a층 b호에 살려면 (a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람들을 데려와 ㅅ라아야 한다.

        int[][] arr = new int[15][15];

        for(int j = 1; j < 15 ; j++){
            arr[0][j] = j;
        }

        for(int i = 1; i < 15 ; i++){
            int sum = 0;
            for(int j = 1; j < 15 ; j++){
                sum += arr[i-1][j];
                arr[i][j] =  sum;
            }
        }

        int T;

        T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T ; t++){
            int k,n;


            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());

            sb.append(arr[k][n]).append("\n");


        }

        System.out.println(sb);

    }
}