import java.io.*;
import java.util.*;

class Point{
    int y,x;
    Point(int y, int x){
        this.y =y;
        this.x =x;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n,m;
        boolean[][] arr;

        ////////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new boolean[n][m];

        for(int i =0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m ; j ++){
                if(st.nextToken().equals("0"))
                    arr[i][j] = false;
                else
                    arr[i][j] = true;
            }
        }

        /////////////////////////////////////////////////////////////////////////////

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        int count =0;
        int max = 0;

        for(int i =0 ; i < n ; i++){
            for(int j =0 ; j < m ; j++){
                if(arr[i][j] && !visited[i][j]){

                    count ++;
                    int sub_max = 0;

                    queue.add(new Point(i,j));
                    visited[i][j]=true;

                    while(!queue.isEmpty()){
                        Point tmp = queue.remove();

                        sub_max++;

                        if(tmp.y-1>=0 && arr[tmp.y-1][tmp.x] && !visited[tmp.y-1][tmp.x]) {
                            visited[tmp.y-1][tmp.x]=true;
                            queue.add(new Point(tmp.y - 1, tmp.x));
                        }

                        if(tmp.y+1<arr.length && arr[tmp.y+1][tmp.x] && !visited[tmp.y+1][tmp.x]) {
                            visited[tmp.y+1][tmp.x]=true;
                            queue.add(new Point(tmp.y + 1, tmp.x));
                        }

                        if(tmp.x-1>=0 && arr[tmp.y][tmp.x-1] && !visited[tmp.y][tmp.x-1]) {
                            visited[tmp.y][tmp.x-1]=true;
                            queue.add(new Point(tmp.y, tmp.x - 1));
                        }

                        if(tmp.x+1<arr[0].length && arr[tmp.y][tmp.x+1] && !visited[tmp.y][tmp.x+1]) {
                            visited[tmp.y][tmp.x+1]=true;
                            queue.add(new Point(tmp.y, tmp.x + 1));
                        }

                    }

                    max = Math.max(max,sub_max);


                }
            }
        }

        System.out.println(count);
        System.out.println(max);


    }
}