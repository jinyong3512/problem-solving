import java.io.*;
import java.util.*;

class Data {
    int number;
    long order;
    int depth;

    Data(int number, long order, int depth) {
        this.number = number;
        this.order = order;
        this.depth = depth;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int A, B;

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            ////////////////////////////////////////////////////////////////

            Queue<Data> queue = new LinkedList<>();
            boolean[] visited = new boolean[10000];

            queue.add(new Data(A, 0, 0));
            visited[A] = true;

            while (!queue.isEmpty()) {
                Data tmp = queue.remove();

                if (tmp.number == B) {
                    // TODO : sb에 담기!!

                    StringBuilder sb2 = new StringBuilder();

                    for (int i = 0; i < tmp.depth; i++) {

                        if (tmp.order % 4 == 0) {
                            sb2.append("D");
                        } else if (tmp.order % 4 == 1) {
                            sb2.append("S");
                        } else if (tmp.order % 4 == 2) {
                            sb2.append("L");
                        } else if (tmp.order % 4 == 3) {
                            sb2.append("R");
                        }

                        tmp.order /= 4;
                    }

                    sb.append(sb2.reverse()).append("\n");

                    break;
                }


                // D 연산
                int D = (tmp.number * 2) % 10000;
                if (!visited[D]) {
                    visited[D] = true;
                    queue.add(new Data(D, tmp.order * 4 + 0, tmp.depth + 1));
                }

                // S 연산
                int S = tmp.number - 1;
                if (S == -1)
                    S = 9999;
                if (!visited[S]) {
                    visited[S] = true;
                    queue.add(new Data(S, tmp.order * 4 + 1, tmp.depth + 1));
                }

                // L 연산
//                String L = String.valueOf(tmp.number);
//                while (L.length() < 4) {
//                    L = "0" + L;
//                }
//                L = L.substring(1) + L.charAt(0);
//                int l = Integer.parseInt(L);

                int l = (tmp.number % 1000) * 10 + tmp.number / 1000;
                if (!visited[l]) {
                    visited[l] = true;
                    queue.add(new Data(l, tmp.order * 4 + 2, tmp.depth + 1));
                }

                // R 연산
//                String R = String.valueOf(tmp.number);
//                while (R.length() < 4) {
//                    R = "0" + R;
//                }
//                R = R.charAt(R.length() - 1) + R.substring(0, R.length() - 1);
//                int r = Integer.parseInt(R);

                int r = (tmp.number % 10) * 1000 + tmp.number / 10;
                if (!visited[r]) {
                    visited[r] = true;
                    queue.add(new Data(r, tmp.order * 4 + 3, tmp.depth + 1));
                }
            }


        }

        System.out.println(sb);

    }
}