import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 15:50

        int N = Integer.parseInt(br.readLine());
        int[][] dices = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ///////////////////////////////////////////////////////

        // 한 주사위에서 AF를 버리거나 BD를 버리거나 CE를 버리거나

        // A를 맨 바닥에 깐 경우 1 주사위 AF를 버리고 2주사위에서는 숫자 F를 버리고

        solve(N, dices);
    }

    public static void solve(int N, int[][] dices) {

        int answer = 0;

        // 맨바닥이 1일 경우
        for (int k = 1; k <= 6; k++) {
            boolean[][] visited = new boolean[N][7];

            int curNumber = k;

            for (int i = 0; i < N; i++) {

                visited[i][curNumber] = true;

                for (int j = 0; j < 6; j++) {

                    // 바닥 숫자 인덱스 찾기
                    if (dices[i][j] == curNumber) {
                        int offsiteIndex = -1;

                        if (j == 0)
                            offsiteIndex = 5;
                        else if (j == 1)
                            offsiteIndex = 3;
                        else if (j == 2)
                            offsiteIndex = 4;
                        else if (j == 3)
                            offsiteIndex = 1;
                        else if (j == 4)
                            offsiteIndex = 2;
                        else
                            offsiteIndex = 0;

                        visited[i][dices[i][offsiteIndex]] = true;
                        curNumber = dices[i][offsiteIndex];
                        break;
                    }
                }
            }

            int curAnswer = 0;
            for(int i = 0 ; i < N ; i++){
                int max = 0;
                for(int j = 1; j <= 6 ; j++){
                    if(!visited[i][j])
                        max=Math.max(max,j);
                }
                curAnswer += max;

            }

            answer = Math.max(answer,curAnswer);
        }

        System.out.println(answer);
    }
}