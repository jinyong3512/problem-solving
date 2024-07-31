import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String N = br.readLine();

        Character[] tmpN = new Character[N.length()];
        for(int i =0 ; i < N.length(); i++){
            tmpN[i] = N.charAt(i);
        }

        ////////////////////////////////////

        Arrays.sort(tmpN,new Comparator<Character>(){
            @Override
            public int compare(Character o1, Character o2){
                return (int)o2-o1;
            }
        });

        for(int i =0 ; i < tmpN.length; i++)
            sb.append(tmpN[i]);
        System.out.println(sb);

    }
}
