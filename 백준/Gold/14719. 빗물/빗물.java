import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 24:02 ~ 24:12 
        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
            int height = Integer.parseInt(st.nextToken());
            for (int i = 0; i < height; i++) {
                arr[H - 1 - i][j] = true;
            }
        }

//        for(int i = 0 ; i < H ; i++){
//            for(int j = 0 ; j < W; j ++){
//                if(arr[i][j])
//                    System.out.print("1 ");
//                else
//                    System.out.print("0 ");
//            }
//            System.out.println();
//        }

        int answer = 0;

        for (int i = H - 1; i >= 0; i--) {
            int left = 0;
            int right = W - 1;

            for (; left < W; left++) {
                if (arr[i][left])
                    break;
            }

            for (; right >= 0; right--) {
                if (arr[i][right])
                    break;
            }

            if (left == W)
                continue;
            if (left == right)
                continue;

            int count = 0;
            for(int j = left+1 ; j < right; j++){
                if(!arr[i][j])
                    count++;
            }

            answer += (count);
        }

        System.out.println(answer);
    }
}