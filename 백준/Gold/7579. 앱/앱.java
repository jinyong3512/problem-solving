import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 14:35
        
        // N 1~10^2
        // M 1~10^7
        // m 1~10^7
        // c 0~100

        int N, M;
        int[] m;
        int[] c;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N];
        c = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        // 완전 탐색하면 2^N

        // key 비용으로 만들 수 있는 최대 메모리
        HashMap<Integer, Long> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {

            HashMap<Integer, Long> newHashMap = new HashMap<>();
            for (Integer cost : hashMap.keySet()) {
                newHashMap.put(cost, hashMap.get(cost));
            }

            for (Integer cost : hashMap.keySet()) {
                int newCost = cost + c[i];

                if (hashMap.getOrDefault(newCost, 0L) < hashMap.get(cost) + m[i]) {
                    newHashMap.put(newCost, hashMap.get(cost) + m[i]);
                }
            }

            if (newHashMap.getOrDefault(c[i], 0L) < m[i])
                newHashMap.put(c[i], (long) m[i]);

            hashMap = newHashMap;

//            for(Integer cost : hashMap.keySet()){
//                System.out.println("cost = " + cost + " memory = " + hashMap.get(cost));
//            }
//            System.out.println("----------");

        }

        // dp[3] = 30;

        // dp[3] = 30;

        // dp

        int answer = Integer.MAX_VALUE;

        for (Integer cost : hashMap.keySet()) {
            if (hashMap.get(cost) >= M) {
                answer = Math.min(answer, cost);
            }
        }
        System.out.println(answer);

    }
}