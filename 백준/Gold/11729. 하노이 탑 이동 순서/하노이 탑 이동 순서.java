import java.io.*;
import java.util.*;

public class Main {
    
    public static int answer=0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());


        hanoi(sb, N, 1, 2, 3);

        // N개의 원반을 start에서 end로 옮겨라

        // N-1개의 원반을 start에서 sub로 옮겨라

        // start에 있는 하나의 원반을 end로 옮겨라

        // sub에 있는 원반을 end로 옮겨라

        System.out.println(answer);
        System.out.println(sb);

    }

    public static void hanoi(StringBuilder sb, int N, int start, int sub, int end) {

        if(N==1){
            answer++;
            sb.append(start + " " + end + "\n");
            return;
        }

        hanoi(sb, N - 1, start, end, sub);
        answer++;
        sb.append(start + " " + end + "\n");
        hanoi(sb,N - 1, sub,start,end);
    }
}
