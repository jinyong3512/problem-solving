import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] arr;
        int S;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        S = Integer.parseInt(br.readLine());

        ////////////////////////////////////////////////////////

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(Integer value : arr)
            arrayList.add(value);

        for (int i = 0; i < arrayList.size(); i++) {

            int maxIndex = i;
            for(int j = i+1 ; j < arrayList.size(); j++){
                if ( S >= (j-i)){
                    if(arrayList.get(maxIndex) < arrayList.get(j))
                        maxIndex = j;
                }else{
                    break;
                }
            }

            S -= (maxIndex-i);

            arrayList.add(i,arrayList.remove(maxIndex));
        }

        for(Integer value : arrayList)
            System.out.print(value + " ");

    }
}