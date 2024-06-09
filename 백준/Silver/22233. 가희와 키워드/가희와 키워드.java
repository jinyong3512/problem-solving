import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 메모장에 써진 키워드는 모두 다르며 N개가 존재
        // 새로운 글을 쓸때 최대 10개의 키워드에 대해서 글을 작성
        // 작성하고 메모장에서 키워드가 사라진다.

        int N, M;
        HashMap<String, Integer> hashMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++)
            hashMap.put(br.readLine(), 1);

        for (int i = 0; i < M; i++) {
            String inputLine = br.readLine();
            String[] keys = inputLine.split(",");
            for(int j =0 ; j < keys.length ; j++){

                if(hashMap.containsKey(keys[j])){
                    hashMap.remove(keys[j]);
                    N--;
                }else{

                }
            }

            sb.append(N).append("\n");
        }
        System.out.println(sb);
    }
}