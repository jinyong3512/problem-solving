import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 0의 개수 짝수
        // 1의 개수 짝수

        // 절반의 0과 절반의 1을 제거하자!

        String S = br.readLine();

        ArrayList<Integer> zeroIndexes = new ArrayList<>();
        ArrayList<Integer> oneIndexes = new ArrayList<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1') {
                oneIndexes.add(i);
            } else {
                zeroIndexes.add(i);
            }
        }

        boolean[] choices = new boolean[S.length()];
        for(int i =oneIndexes.size()/2 ; i < oneIndexes.size() ; i++){
            choices[oneIndexes.get(i)] = true;
        }

        for(int i = 0 ; i < zeroIndexes.size()/2 ; i++){
            choices[zeroIndexes.get(i)] = true;
        }

        for(int i = 0 ; i < choices.length ; i++){
            if(choices[i])
                sb.append(S.charAt(i));
        }

        System.out.println(sb);

    }
}