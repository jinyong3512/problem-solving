import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        ArrayList<Integer> arrayList;

        /////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arrayList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            arrayList.add(i);

        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int position = Integer.parseInt(st.nextToken()) - 1;

            int frontFind = 0;
            for(int j = 0 ; j < arrayList.size() ; j++){
                if(arrayList.get(j)== position){
                    break;
                }
                else{
                    frontFind+=1;
                }
            }

            int backFind = 1;
            for(int j = arrayList.size()-1 ; j >=0 ; j--){
                if(arrayList.get(j)== position){
                    break;
                }
                else{
                    backFind+=1;
                }
            }

            if(frontFind < backFind){
                for(int j = 0; j < frontFind ; j++){
                    arrayList.add(arrayList.remove(0));
                }
                answer += frontFind;
                arrayList.remove(0);
            }
            else{
                for(int j =0 ; j < backFind; j++){
                    arrayList.add(0,arrayList.remove(arrayList.size()-1));
                }
                answer += backFind;
                arrayList.remove(0);
            }

        }

        System.out.println(answer);


    }
}