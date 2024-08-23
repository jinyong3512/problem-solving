import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        /////////////////////////////////////////////////////

//        String[] rank = new String[M];
        int[][] ranks = new int[M][N];

        for (int i = 0; i < M; i++) {
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            for(int j = 0 ; j < N ; j++)
                hashMap.put(arr[i][j],-1);

            ArrayList<Integer> arrayList = new ArrayList<>();
            for(Integer key: hashMap.keySet())
                arrayList.add(key);
            Collections.sort(arrayList);

            hashMap.clear();
            for(int j = 0 ; j < arrayList.size() ; j++)
                hashMap.put(arrayList.get(j),j);

//            rank[i] = "";
//            for(int j = 0 ; j < N ; j++){
//                rank[i] += hashMap.get(arr[i][j]);
//            }
//            System.out.println(rank[i]);

            for(int j =0 ; j < N ; j++){
                ranks[i][j] = hashMap.get(arr[i][j]);
            }

        }

//        int answer = 0;
//        for(int i =0 ;  i < M ; i++){
//            for(int j = i +1 ; j < M ; j++){
//                if(rank[i].equals(rank[j]))
//                    answer++;
//            }
//        }
//        System.out.println(answer);

        int answer = 0;
        for(int i =0 ; i  < M ; i++){
            for(int j = i +1 ; j < M ; j++){
                if(Arrays.equals(ranks[i],ranks[j]))
                    answer++;
            }
        }
        System.out.println(answer);

    }
}
