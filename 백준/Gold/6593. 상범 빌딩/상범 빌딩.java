import java.io.*;
import java.util.*;

class Point {
    int z, y, x, depth;

    Point(int z, int y, int x, int depth) {
        this.z = z;
        this.y = y;
        this.x = x;
        this.depth = depth;
    }

}

public class Main {

    public static int answer;

    // z-- z++ y-- y++ x-- x++
    public static int[] dz = new int[]{-1, 1, 0, 0, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int L, R, C;
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0)
                break;

            char[][][] arr = new char[L][R][C];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    String inputLine = br.readLine();
                    for (int k = 0; k < arr[i][j].length; k++) {
                        arr[i][j][k] = inputLine.charAt(k);
                    }
                }
                String trashLine = br.readLine();
            }

            ////////////////////////////////////////////////

//            for (int i = 0; i < L; i++) {
//                for (int j = 0; j < R; j++) {
//                    for (int k = 0; k < C; k++) {
//                        System.out.print(arr[i][j][k]);
//                    }
//                    System.out.println();
//                }
//            }

            Point cur = null;

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    for (int k = 0; k < arr[0][0].length; k++) {
                        if (arr[i][j][k] == 'S')
                            cur = new Point(i, j, k, 0);
                    }
                }
            }

            Queue<Point> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[arr.length][arr[0].length][arr[0][0].length];

            queue.add(cur);
            visited[cur.z][cur.y][cur.x] = true;

            int answer = Integer.MAX_VALUE;

            while (!queue.isEmpty()) {
                Point curPoint = queue.remove();

                if (arr[curPoint.z][curPoint.y][curPoint.x] == 'E') {
                    answer = curPoint.depth;
                    break;
                }

                for (int direction = 0; direction < 6; direction++) {
                    int newZ = curPoint.z + dz[direction];
                    int newY = curPoint.y + dy[direction];
                    int newX = curPoint.x + dx[direction];

                    if (newZ >= 0 && newZ < arr.length && newY >= 0 && newY < arr[0].length && newX >= 0 && newX < arr[0][0].length) {
                        if(!visited[newZ][newY][newX] && arr[newZ][newY][newX]!='#'){
                            visited[newZ][newY][newX]=true;
                            queue.add(new Point(newZ,newY,newX,curPoint.depth+1));
                        }
                    }
                }

            }

            if(answer==Integer.MAX_VALUE){
                sb.append("Trapped!").append("\n");
            }else{
                sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
            }


        }

        System.out.println(sb);

    }
}