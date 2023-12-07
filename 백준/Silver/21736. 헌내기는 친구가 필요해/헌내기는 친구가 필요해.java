import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        //////////////////////////////////////////////////////////////////

        int N, M;
        char[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = inputLine.charAt(j);
            }
        }

        //////////////////////////////////////////////////////////////////////

        int curY = -1;
        int curX = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'I') {
                    curY = i;
                    curX = j;
                }
            }
        }

        boolean[][] visited = new boolean[arr.length][arr[0].length];

        dfs(curY, curX, visited, arr);
        
        
        if(answer==0){
            System.out.println("TT");
        }
        else{
            System.out.println(answer);
        }
    }

    public static void dfs(int curY, int curX, boolean[][] visited, char[][] arr) {

        if(arr[curY][curX]=='P')
            answer+=1;

        visited[curY][curX] = true;

        for (int direction = 0; direction < 4; direction++) {
            int newY = curY + dy[direction];
            int newX = curX + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[newY].length) {
                continue;
            }

            if (!visited[newY][newX] && arr[newY][newX] != 'X') {
                dfs(newY,newX,visited,arr);
            }
        }

    }
}