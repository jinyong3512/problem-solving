import java.io.*;
import java.util.*;

class Data {
    int x, y;

    Data(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        Data[] datas = new Data[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            datas[i] = new Data(x, y);
        }

        Arrays.sort(datas, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.y < o2.y)
                    return -1;
                else if (o1.y == o2.y)
                    return o1.x - o2.x;
                else
                    return 1;
            }
        });

        for(int i =0 ; i < N ; i++){
            sb.append(datas[i].x).append(" ").append(datas[i].y).append("\n");
        }
        System.out.println(sb);

    }
}
