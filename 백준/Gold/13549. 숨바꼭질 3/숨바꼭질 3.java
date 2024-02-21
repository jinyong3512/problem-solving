import java.io.*;
import java.util.*;

class Data {
    int number;
    int count;

    Data(int number, int count) {
        this.number = number;
        this.count = count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 0~10^5
        // K 0~10^5
        // 1초후에 X-1 ,X+1 0초후에 2*X

        int N, K;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////

        PriorityQueue<Data> pQ = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.count < o2.count) {
                    return -1;
                } else if (o1.count == o2.count)
                    return 0;
                else
                    return 1;
            }
        });
        pQ.add(new Data(N, 0));

        int[] visited = new int[100001];
        for (int i = 0; i <= 100000; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        visited[N] = 0;

        while (!pQ.isEmpty()) {
            Data tmp = pQ.remove();

            if (tmp.number == K) {
                sb.append(tmp.count);
                break;
            }

            if (tmp.number * 2 <= 100000 && visited[tmp.number*2] > tmp.count) {
                visited[tmp.number * 2] = tmp.count;
                pQ.add(new Data(tmp.number * 2, tmp.count));
            }

            if (tmp.number - 1 >= 0 && visited[tmp.number-1] > tmp.count+1) {
                visited[tmp.number - 1] = tmp.count+1;
                pQ.add(new Data(tmp.number - 1, tmp.count + 1));
            }
            if (tmp.number + 1 <= 100000 && visited[tmp.number+1] > tmp.count+1) {
                visited[tmp.number + 1] = tmp.count+1;
                pQ.add(new Data(tmp.number + 1, tmp.count + 1));
            }

        }

        System.out.println(sb);


    }
}