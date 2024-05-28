import java.io.*;
import java.util.*;

class Data {
    int sum;
    int count;
    int lastSubmit;
    int[] score;
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;
        T = Integer.parseInt(br.readLine());

        for (int a = 0; a < T; a++) {
            int n, k, t, m;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken()) -1;
            m = Integer.parseInt(st.nextToken());

            // 팀 ID, 문제 번호 j, 흭득한 점수 s

            Data[] datas = new Data[n];
            for (int i = 0; i < n; i++) {
                datas[i] = new Data();
                datas[i].score = new int[k];
            }

            for (int M = 0; M < m; M++) {

                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken())-1;
                int j = Integer.parseInt(st.nextToken())-1;
                int s = Integer.parseInt(st.nextToken());

                datas[i].lastSubmit = M;
                datas[i].count++;

                if (datas[i].score[j] < s) {
                    datas[i].sum += (s - datas[i].score[j]);
                    datas[i].score[j] = s;
                }

            }

            int answer = 1;
            for(int i =0 ; i < n ; i ++){
                if(i == t)
                    continue;
                if(datas[i].sum > datas[t].sum){
                    answer++;
                }else if (datas[i].sum == datas[t].sum){

                    if(datas[i].count < datas[t].count){
                        answer++;
                    }else if ( datas[i].count == datas[t].count){

                        if(datas[i].lastSubmit < datas[t].lastSubmit){
                            answer++;
                        }

                    }


                }
            }

            sb.append(answer).append("\n");


        }
        System.out.println(sb);

    }
}
