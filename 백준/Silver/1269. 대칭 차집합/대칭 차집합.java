import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int ANum = Integer.parseInt(st.nextToken());
        int BNum = Integer.parseInt(st.nextToken());
        
        HashMap<Integer, Boolean> A = new HashMap<>();
        HashMap<Integer, Boolean> B = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < ANum ; i++){
            A.put(Integer.parseInt(st.nextToken()),true);
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < BNum ; i++){
            B.put(Integer.parseInt(st.nextToken()),true);
        }
        
        /////////////////////////////////////////////////
        
        int answer = 0;
        for(Integer key: A.keySet()){
            if(B.containsKey(key)){
                B.remove(key);
            }else
                answer++;
        }
        
        answer += B.size();
        
        System.out.println(answer);
        
    }
}