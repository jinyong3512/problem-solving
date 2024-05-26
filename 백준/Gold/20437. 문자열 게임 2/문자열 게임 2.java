import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String W;
            int K;

            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            ////////////////////////////////////

            HashMap<Character, ArrayList<Integer>> hashMap = new HashMap<>();

            for (int i = 0; i < W.length(); i++) {

                if (!hashMap.containsKey(W.charAt(i))) {
                    hashMap.put(W.charAt(i), new ArrayList<>());
                }

                hashMap.get(W.charAt(i)).add(i);

            }

            int answer3 = Integer.MAX_VALUE;
            int answer4 = Integer.MIN_VALUE;

            for (Character key : hashMap.keySet()) {
                ArrayList<Integer> curArrayList = hashMap.get(key);

                int leftIndex =0 ;
                int rightIndex = K-1;

                while( rightIndex < curArrayList.size()){
                    int length = curArrayList.get(rightIndex) -curArrayList.get(leftIndex)+1;
                    answer3 = Math.min(answer3,length);
                    answer4 = Math.max(answer4,length);
                    rightIndex++;
                    leftIndex++;
                }

            }

            if (answer3 != Integer.MAX_VALUE)
                sb.append(answer3).append(" ").append(answer4).append("\n");
            else
                sb.append("-1").append("\n");
        }
        System.out.println(sb);

    }
}
