import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        N = Integer.parseInt(br.readLine());

        ArrayList<String> in = new ArrayList<>();
        ArrayList<String> out = new ArrayList<>();

        for(int i =0 ; i < N ; i ++){
            in.add(br.readLine());
        }
        for(int i =0 ; i < N ; i ++){
            out.add(br.readLine());
        }

        int answer = 0;

        while(!in.isEmpty()){
            
            if( in.get(0).equals(out.get(0))){
                in.remove(0);
                out.remove(0);
            }else{
                answer++;
                in.remove(out.remove(0));
            }


        }

        System.out.println(answer);



    }

}