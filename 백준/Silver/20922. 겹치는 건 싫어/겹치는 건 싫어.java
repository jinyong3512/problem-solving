import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        int answer = 0;
        int leftIndex = 0;
        int rightIndex = -1;

        while (true) {

            rightIndex++;
            if (rightIndex >= arr.length)
                break;

            int curValue = hashMap.getOrDefault(arr[rightIndex], 0);
            hashMap.put(arr[rightIndex], curValue + 1);

            if (hashMap.get(arr[rightIndex]) > K) {

                while (true) {
                    hashMap.put(arr[leftIndex], hashMap.get(arr[leftIndex]) - 1);
                    if (arr[leftIndex] == arr[rightIndex]) {
                        leftIndex++;
                        break;
                    } else {
                        leftIndex++;
                    }
                }

            } else {
                answer = Math.max(answer, rightIndex - leftIndex + 1);
            }
        }

        System.out.println(answer);

    }
}