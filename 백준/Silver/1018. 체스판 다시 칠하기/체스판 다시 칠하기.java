import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        ///////////////////////////////////

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {

                int count = 0;

                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if((y+x) % 2 == 0){
                            if(arr[i+y][j+x]=='B')
                                count++;
                        }else{
                            if(arr[i+y][j+x]=='W')
                                count++;
                        }
                    }
                }

                answer = Math.min(answer,count);

                count = 0;

                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if((y+x) % 2 == 0){
                            if(arr[i+y][j+x]=='W')
                                count++;
                        }else{
                            if(arr[i+y][j+x]=='B')
                                count++;
                        }
                    }
                }

                answer = Math.min(answer,count);

            }
        }

        System.out.println(answer);


    }
}
