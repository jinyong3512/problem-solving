import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        long answer = 0;

        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        int leftIndex = 0;
        int rightIndex = -1;

        while (true) {
            rightIndex++;
            if (rightIndex == N)
                break;

            if (hashMap.containsKey(arr[rightIndex])) {

                while(true){

                    answer += (rightIndex - leftIndex);

                    if(arr[leftIndex] == arr[rightIndex]){
                        leftIndex++;
                        break;
                    }else{
                        hashMap.remove(arr[leftIndex]);
                        leftIndex++;
                    }
                }
            } else {
                hashMap.put(arr[rightIndex], true);
            }

        }

        while(leftIndex < N){
            answer += (rightIndex- leftIndex);
            leftIndex++;
        }

        System.out.println(answer);

    }
}