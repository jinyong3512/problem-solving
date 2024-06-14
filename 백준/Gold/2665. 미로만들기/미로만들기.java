import java.io.*;
import java.util.*;

class Vertex{
    int y,x,count;

    Vertex(int y,int x, int count){
        this.y=y;
        this.x=x;
        this.count=count;
    }
}

public class Main {

    public static int[] dy = new int[]{-1,1,0,0};
    public static int[] dx = new int[]{0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;
        boolean[][] whites;

        n = Integer.parseInt(br.readLine());

        whites = new boolean[n][n];
        for(int i =0 ; i < n ; i++){
            String inputLine = br.readLine();
            for(int j =0 ; j < n ; j++){
                if(inputLine.charAt(j)=='1')
                    whites[i][j]=true;
            }
        }

        ///////////////////////////////////////

        long[][] distances = dijkstra(whites,0,0);

        System.out.println(distances[n-1][n-1]);

    }

    public static long[][] dijkstra(boolean[][] whites,int startY,int startX){
        PriorityQueue<Vertex> pQ = new PriorityQueue<>(new Comparator<Vertex>(){
            @Override
            public int compare(Vertex o1,Vertex o2){
                return o1.count-o2.count;
            }
        });
        boolean[][] visited = new boolean[whites.length][whites[0].length];
        long[][] distances = new long[whites.length][whites[0].length];
        for(int i =0 ; i < whites.length ; i ++){
            for(int j =0 ; j < whites.length ; j++)
                distances[i][j]= Long.MAX_VALUE;
        }

        pQ.add(new Vertex(startY,startX,0));

        while(!pQ.isEmpty()){
            Vertex curVertex = pQ.remove();

            if(visited[curVertex.y][curVertex.x])
                continue;

            visited[curVertex.y][curVertex.x] = true;
            distances[curVertex.y][curVertex.x]= curVertex.count;

            for(int direction = 0;  direction< 4; direction++){

                int newY = curVertex.y + dy[direction];
                int newX = curVertex.x + dx[direction];

                if(newY<0 || newY>= whites.length || newX<0 || newX>= whites[0].length)
                    continue;

                if(whites[newY][newX])
                    pQ.add(new Vertex(newY,newX,curVertex.count));
                else
                    pQ.add(new Vertex(newY,newX,curVertex.count+1));


            }


        }

        return distances;
    }
}