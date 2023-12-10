import java.io.*;
import java.util.*;

class Point {
    int index;
    int score;

    Point(int index, int score) {
        this.index = index;
        this.score = score;
    }
}

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        // 1:10 시작

        ArrayList<ArrayList<Point>> graph = new ArrayList<>();
        // 0 출발
        // 1 부터 20까지는 밖
        // 21 도착
        // 가로 5 -> 22 -> 23 ->  24 ->  25  <- 26 <- 27 <- 28 <- 15
        // 세로 10 29 30  |  25  | 31 32  | 20 21
        for (int i = 0; i <= 32; i++) {
            graph.add(new ArrayList<>());
        }

        // 0 ->1 , 1 ->2 ....... 19 -> 20
        for (int i = 0; i < 20; i++)
            graph.get(i).add(new Point(i + 1, 2 * (i + 1)));

        graph.get(20).add(new Point(21, 0));

        // 가로
        graph.get(5).add(new Point(22, 13));
        graph.get(22).add(new Point(23, 16));
        graph.get(23).add(new Point(24, 19));
        graph.get(24).add(new Point(25, 25));

        graph.get(15).add(new Point(28, 28));
        graph.get(28).add(new Point(27, 27));
        graph.get(27).add(new Point(26, 26));
        graph.get(26).add(new Point(25, 25));

        // 세로
        graph.get(10).add(new Point(29, 22));
        graph.get(29).add(new Point(30, 24));
        graph.get(30).add(new Point(25, 25));

        graph.get(25).add(new Point(31, 30));
        graph.get(31).add(new Point(32, 35));
        graph.get(32).add(new Point(20, 40));


        ///////////////////////////////////////////////////////////////////

        int score = 0;
        int[] horses = new int[4];
        int[] dices = new int[10];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        recursion(graph, dices, 0, 0, horses);

        System.out.println(answer);

    }

    public static void recursion(ArrayList<ArrayList<Point>> graph, int[] dices, int diceIndex, int score, int[] horses) {
        if (diceIndex == 10) {
            answer = Math.max(answer, score);

            return;
        }

        answer = Math.max(answer, score);



        // 한말씩 이동해보자
        for (int i = 0; i < 4; i++) {

            // 끝 말 선택하면 pass
            if (horses[i] == 21)
                continue;

            int curIndex = horses[i];
            int diceNumber = dices[diceIndex];
            int tmpScore = score;

            if (curIndex == 5 || curIndex == 10 || curIndex == 15) {

                diceNumber--;
                if (diceNumber == 0)
                    tmpScore += graph.get(curIndex).get(1).score;

                curIndex = graph.get(curIndex).get(1).index;
            }

            for (int j = 0; j < diceNumber; j++) {
                if (curIndex == 21)
                    break;

                if (diceNumber - 1 == j) {
                    tmpScore += graph.get(curIndex).get(0).score;
                }

                curIndex = graph.get(curIndex).get(0).index;
            }


            boolean can = true;
            for (int j = 0; j < 4; j++) {
                if (curIndex == horses[j] && horses[j] != 21)
                    can = false;
            }

            if (can) {
                int[] tmpHorses = new int[4];
                for (int j = 0; j < 4; j++) {
                    tmpHorses[j] = horses[j];
                }

                tmpHorses[i] = curIndex;
                recursion(graph, dices, diceIndex + 1, tmpScore, tmpHorses);
            }
        }


    }
}
