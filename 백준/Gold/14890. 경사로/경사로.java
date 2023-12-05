import java.io.*;
import java.util.*;

// 21:15 도전

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N,L;
        int[][] arr;

        ////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////

        int answer =0 ;

        boolean[][] visited = new boolean[N][N];

        // 가로 한줄 씩 먼저 검사하자
        for(int i =0 ; i < N ; i++){
            int p1 = 0;
            int p2 = 0;

            while(p1!=N-1){
                // p2가 먼저 한 칸 가
                p2++;

                // 어 높이 차이가 1보다 크다 걍 탈락
                if(Math.abs(arr[i][p2]-arr[i][p1])>1){
                    break;
                }
                // 높아 졌다
                else if ( arr[i][p2] - arr[i][p1] ==1){
                    boolean can = true;
                    for(int k = 0 ; k <  L  ; k++){
                        if(p1-k <0 || arr[i][p1] != arr[i][p1-k] || visited[i][p1-k])
                            can = false;
                    }
                    if(can){
                        for(int k = 0 ; k <  L  ; k++){
                            visited[i][p1-k] = true;
                        }
                        p1 = p2;
                    }else{
                        break;
                    }

                }
                // 낮아 졌다
                else if ( arr[i][p2] - arr[i][p1] == -1) {
                    boolean can = true;
                    for(int k = 0 ; k <  L  ; k++){
                        if(p2+k >=N ||arr[i][p2] != arr[i][p2+k] || visited[i][p2+k])
                            can = false;
                    }

                    if(can){
                        for(int k = 0 ; k <  L  ; k++){
                            visited[i][p2+k] = true;
                        }
                        p1 = p2+L-1;
                        p2 = p2+L-1;
                    }else{
                        break;
                    }
                }
                // 높이 차이가 같다!
                else{
                    p1++;
                }
            }

            if(p1==N-1){
                answer+=1;
            }

        }

        visited = new boolean[N][N];

        // 세로 한줄 씩 먼저 검사하자
        for(int i =0 ; i < N ; i++){
            int p1 = 0;
            int p2 = 0;

            while(p1!=N-1){
                // p2가 먼저 한 칸 가
                p2++;

                // 어 높이 차이가 1보다 크다 걍 탈락
                if(Math.abs(arr[p2][i]-arr[p1][i])>1){
                    break;
                }
                // 높아 졌다
                else if ( arr[p2][i] - arr[p1][i] ==1){
                    boolean can = true;
                    for(int k = 0 ; k <  L  ; k++){
                        if(p1-k < 0 ||arr[p1][i] != arr[p1-k][i] || visited[p1-k][i])
                            can = false;
                    }
                    if(can){
                        for(int k = 0 ; k <  L  ; k++){
                            visited[p1-k][i] = true;
                        }
                        p1 = p2;
                    }else{
                        break;
                    }

                }
                // 낮아 졌다
                else if ( arr[p2][i] - arr[p1][i] == -1) {
                    boolean can = true;
                    for(int k = 0 ; k <  L  ; k++){
                        if(p2+k >=N ||arr[p2][i] != arr[p2+k][i] || visited[p2+k][i])
                            can = false;
                    }

                    if(can){
                        for(int k = 0 ; k <  L  ; k++){
                            visited[p2+k][i] = true;
                        }
                        p1 = p2+L-1;
                        p2 = p2+L-1;
                    }else{
                        break;
                    }
                }
                // 높이 차이가 같다!
                else{
                    p1++;
                }
            }

            if(p1==N-1){
                answer+=1;
            }

        }

        System.out.println(answer);


    }
}