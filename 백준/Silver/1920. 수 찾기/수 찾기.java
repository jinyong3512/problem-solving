import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N : 1 ~ 100,000 10^5
        // M : 1 ~ 100,000 10^5


        int N = Integer.parseInt(br.readLine());

        HashMap<Long, Boolean> aHashMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aHashMap.put(Long.parseLong(st.nextToken()), true);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < M ; i++){
            if(aHashMap.containsKey(Long.parseLong(st.nextToken()))){
                sb.append("1").append("\n");
            }else
                sb.append("0").append("\n");
        }
        System.out.println(sb);


    }
}
