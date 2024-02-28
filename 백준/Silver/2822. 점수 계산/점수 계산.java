import java.io.*;
import java.util.*;

class Data {
    int score;
    int index;

    Data(int score, int index) {
        this.score = score;
        this.index = index;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] score = new int[8];
        for (int i = 0; i < 8; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        //////////////////////////////////////////////////////

        ArrayList<Data> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            arrayList.add(new Data(score[i], i));
        }

        Collections.sort(arrayList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o2.score - o1.score;

            }
        });

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += arrayList.get(i).score;
        }
        System.out.println(sum);

        ArrayList<Integer> answers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            answers.add(arrayList.get(i).index+1);
//            System.out.print(arrayList.get(i).index + " ");
        }

        Collections.sort(answers);

        for(Integer answer : answers){
            System.out.print(answer+" ");
        }


    }
}