import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 상근 창영

        // 마지막돌 가져가는 사람 게임 짐

        // 이기는 사람은??

        // 1개 -> 상근 패배
        // 2개 -> 창영 패배
        // 3개 -> 상근 패배
        // 4개 -> 창영 패배
        
        int N;
        
        N = Integer.parseInt(br.readLine());
        
        if(N%2==1){
            System.out.println("CY");
        }
        else{
            System.out.println("SK");
        }

    }
}