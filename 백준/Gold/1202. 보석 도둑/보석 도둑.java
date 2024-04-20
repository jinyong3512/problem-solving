import java.io.*;
import java.util.*;

class Data {
    int M;
    int V;
}

class Bag {
    int nowValue;
    int bagWeight;

    Bag(int nowValue, int bagWeight) {
        this.nowValue = nowValue;
        this.bagWeight = bagWeight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N,K 1 ~ 30만
        // M,V 0 ~ 100만
        // C 1 ~ 1억
        // 100,000,000
        // 10^8 1억

        int N, K;
        int[] Ms;
        int[] Vs;
        int[] Cs;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Ms = new int[N];
        Vs = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Ms[i] = Integer.parseInt(st.nextToken());
            Vs[i] = Integer.parseInt(st.nextToken());
        }

        Cs = new int[K];
        for (int i = 0; i < K; i++) {
            Cs[i] = Integer.parseInt(br.readLine());
        }

        ////////////////////////////////////////////

        // 작전 1
        // 가치가 높은것, 가벼운 순 으로 정렬한다
        // 모든 가방을 돌면서 제일 이득인 가방에 넣는다
        // O(N*K) 시간초과


        // 작전 2
        // 현재 가방의 상태를 들고 다닌다 nowValue , weight;
        // 보석 무게 낮은거- 가치 높은거
        // 가방 현가치 낮은거 - 가방이 가벼운거

        ArrayList<Data> datas = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Data newData = new Data();
            newData.M = Ms[i];
            newData.V = Vs[i];
            datas.add(newData);
        }

        Collections.sort(datas, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.M > o2.M) {
                    return 1;
                } else if (o1.M == o2.M) {
                    if (o1.V > o2.V) {
                        return -1;
                    } else if (o1.V == o2.V) {
                        return 0;
                    } else
                        return 1;
                } else {
                    return -1;
                }
            }
        });

        Arrays.sort(Cs);

        PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 == o2)
                    return 0;
                else
                    return -1;
            }
        });

        long answer =0;

        int index = 0;
        for (int i = 0; i < K; i++) {
            int curBagWeight = Cs[i];

            while (true) {

                if (index >= datas.size())
                    break;

                if (datas.get(index).M <= curBagWeight) {
                    pQ.add(datas.get(index).V);
                    index++;
                } else {
                    break;
                }
            }

            if(!pQ.isEmpty())
                answer+= pQ.remove();
        }


        System.out.println(answer);

    }
}
