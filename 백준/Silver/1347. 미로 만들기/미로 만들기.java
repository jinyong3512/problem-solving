import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int[] dx = new int[]{0, 1, 0, -1};

    // 상 우 하 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();

        ////////////////////////////////////////

        boolean[][] visited = new boolean[100][100];
        int curY = 50;
        int curX = 50;
        int curDirection = 2;

        visited[curY][curX] = true;

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == 'R')
                curDirection = (curDirection + 1) % 4;
            else if (line.charAt(i) == 'L')
                curDirection = (curDirection - 1 + 4) % 4;
            else {
                curY = curY + dy[curDirection];
                curX = curX + dx[curDirection];
                visited[curY][curX] = true;
            }
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if(visited[i][j]){

                    minX = Math.min(minX,j);
                    minY = Math.min(minY,i);
                    maxX = Math.max(maxX,j);
                    maxY = Math.max(maxY,i);

                }
            }
        }


        for(int i = minY ; i <= maxY ; i++){
            for(int j = minX ; j <=maxX ; j++){
                if(visited[i][j])
                    sb.append(".");
                else
                    sb.append("#");
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }
}
