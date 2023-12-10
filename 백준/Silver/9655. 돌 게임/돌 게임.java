import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 돌이 5개라면

        // 상근턴 -1 -3
        // 창영턴 -1 -3

        // 한턴이 돌때 그러면 -2 -4 -5 -6 이 만들어지네

        if (N%2 == 0){
            System.out.println("CY");
        }
        else{
            System.out.println("SK");
        }

    }
}
