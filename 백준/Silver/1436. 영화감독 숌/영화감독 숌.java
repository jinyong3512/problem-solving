import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N번째 영화의 제목에 들어간 수를 출력하는 프로그램
        // 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수

        int N = Integer.parseInt(br.readLine());
        int curN = 0;

        int num = 666;
        while (true) {

            String numStr = String.valueOf(num);
            for(int i =0 ; i  < numStr.length() -2 ; i++){
                if(numStr.charAt(i)=='6' && numStr.charAt(i+1)=='6' && numStr.charAt(i+2)=='6'){
                    curN++;
                    break;

                }

            }

            if (N == curN) {
                System.out.println(num);
                break;
            }else{
                num++;
            }
        }

    }
}
