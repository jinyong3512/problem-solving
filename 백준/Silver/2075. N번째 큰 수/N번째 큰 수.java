import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        int[] heights = new int[N];
        for(int j = 0 ; j < N ; j++)
            heights[j] = N-1;

        int count = 1;
        while(true){

            int maxY = heights[0];
            int maxX = 0;

            for(int j = 1 ; j < N; j++){
                if(arr[maxY][maxX] < arr[heights[j]][j]){
                    maxY = heights[j];
                    maxX = j;
                }
            }

            if(count == N){
                System.out.println(arr[maxY][maxX]);
                break;
            }

            heights[maxX]--;


            count++;
        }

    }
}
