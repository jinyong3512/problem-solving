import java.io.*;
import java.util.*;

class Egg {
    int power;
    int weight;

    Egg(int power, int weight) {
        this.power = power;
        this.weight = weight;
    }
}

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 내구도, 무게
        // 7, 5
        // 3, 4

        // 가장 왼쪽 계란으로
        // 다른 하나를 친다. (하나만)
        // 한칸 오른쪽 계란을 든다.

        int N = Integer.parseInt(br.readLine());

        Egg[] eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        combination(N, eggs, 0, 0);
        System.out.println(answer);

    }

    public static void combination(int N, Egg[] eggs, int index, int count) {

        if (index == N) {
            answer = Math.max(answer, count);
            return;
        }

        if (eggs[index].power <= 0) {
            combination(N, eggs, index + 1, count);
        } else {

            boolean hit = false;

            for (int i = 0; i < N; i++) {
                if (i == index)
                    continue;

                if (eggs[i].power > 0) {
                    hit = true;

                    eggs[index].power -= eggs[i].weight;
                    eggs[i].power -= eggs[index].weight;

                    if (eggs[index].power <= 0 && eggs[i].power <= 0)
                        combination(N, eggs, index + 1, count + 2);
                    else if (eggs[index].power <= 0)
                        combination(N, eggs, index + 1, count + 1);
                    else if (eggs[i].power <= 0)
                        combination(N, eggs, index + 1, count + 1);
                    else
                        combination(N, eggs, index + 1, count);

                    eggs[index].power += eggs[i].weight;
                    eggs[i].power += eggs[index].weight;

                }
            }

            if (!hit) {
                combination(N, eggs, index + 1, count);
            }


        }

    }
}
