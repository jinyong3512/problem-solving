import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        /////////////////////////////////////////////

        int answer = 2;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(c, 1);

        for (int i = 0; i < k; i++)
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);

        answer = Math.max(answer, hashMap.size());

        int next = (k)%N;
        int prev = 0;

        while (true) {

            hashMap.put(arr[next], hashMap.getOrDefault(arr[next], 0) + 1);
            hashMap.put(arr[prev], hashMap.get(arr[prev]) - 1);

            if (hashMap.get(arr[prev]) == 0)
                hashMap.remove(arr[prev]);

            answer = Math.max(answer,hashMap.size());

            next = (next+1)%N;
            prev++;
            if (prev == N)
                break;

        }

        System.out.println(answer);

    }
}