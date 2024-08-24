import java.io.*;
import java.util.*;

class Water {
    int start;
    int end;

    public Water(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Water[] waters = new Water[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            waters[i] = new Water(start, end);
        }

        /////////////////////////////////////////////////
        /////////////////////////////////////////////////
        /////////////////////////////////////////////////

        // N 1~10^4
        // L 1~10^6
        // start end 0 ~ 10^9 10억

        int last = -1;
        int answer = 0;

        Arrays.sort(waters, new Comparator<Water>() {
            @Override
            public int compare(Water o1, Water o2) {
                return o1.start - o2.start;
            }
        });

        for (int i = 0; i < waters.length; i++) {
            int curStart = waters[i].start;
            int curEnd = waters[i].end - 1;

            if(last >= curEnd)
                continue;

            if (curStart > last) {

            } else if (curStart == last) {
                curStart = last + 1;
            } else {
                curStart = last + 1;
            }

            int indexCount = (curEnd - curStart + 1);

            if (indexCount % L == 0) {
                answer += (indexCount / L);
                last = curStart + indexCount - 1;
            } else {
                answer += (indexCount / L + 1);
                last = curStart + (indexCount / L + 1) * L - 1;
            }


        }

        System.out.println(answer);

    }
}
