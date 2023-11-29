import java.io.*;
import java.util.*;

class Point {
    int y, x, sum, depth;

    Point(int y, int x, int sum, int depth) {
        this.y = y;
        this.x = x;
        this.sum = sum;
        this.depth = depth;

    }
}

class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // TODO : 문제 설명이 어렵지 테르토미노 : 정사각형 4개로 만들 수 있는 모든 도형을 말한다.
        // 하나만 놓으니까 결국 4방향 탐색하는 문제 아님?
        // 백트래킹 적용 가능? 흠 힘들것 같은데 모두 다 가봐야 알것같은 기분이야
        // 해당 점에 같은 기회로 더 낮은 점수로 도착했다? 이건 짤이지

        int N, M;
        int[][] arr;

        ////////////////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////

        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                visited[i][j] = true;
                recursion(arr, visited, i, j, arr[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    public static void recursion(int[][] arr, boolean[][] visited, int i, int j, int sum, int depth) {


        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        if (depth == 2) {
            // 위 왼쪽
            if (i - 1 >= 0 && j - 1 >= 0 && !visited[i - 1][j] && !visited[i][j - 1])
                answer = Math.max(answer, sum + arr[i - 1][j] + arr[i][j - 1]);


            // 위 아래
            if (i - 1 >= 0 && i + 1 < arr.length && !visited[i - 1][j] && !visited[i + 1][j])
                answer = Math.max(answer, sum + arr[i - 1][j] + arr[i + 1][j]);

            // 위 오른쪽
            if (i - 1 >= 0 && j + 1 < arr[0].length && !visited[i - 1][j] && !visited[i][j + 1])
                answer = Math.max(answer, sum + arr[i - 1][j] + arr[i][j + 1]);

            // 왼쪽 아래
            if (j - 1 >= 0 && i + 1 < arr.length && !visited[i][j - 1] && !visited[i + 1][j])
                answer = Math.max(answer, sum + arr[i][j - 1] + arr[i + 1][j]);

            // 왼쪽 오른쪽
            if (j - 1 >= 0 && j + 1 < arr[0].length && !visited[i][j - 1] && !visited[i][j + 1])
                answer = Math.max(answer, sum + arr[i][j - 1] + arr[i][j + 1]);

            // 아래 오른쪽
            if (i + 1 < arr.length && j + 1 < arr[0].length && !visited[i + 1][j] && !visited[i][j + 1])
                answer = Math.max(answer, sum + arr[i + 1][j] + arr[i][j + 1]);
        }


        if (i - 1 >= 0 && !visited[i - 1][j]) {
            visited[i - 1][j] = true;
            recursion(arr, visited, i - 1, j, sum + arr[i - 1][j], depth + 1);
            visited[i - 1][j] = false;
        }

        if (i + 1 < arr.length && !visited[i + 1][j]) {
            visited[i + 1][j] = true;
            recursion(arr, visited, i + 1, j, sum + arr[i + 1][j], depth + 1);
            visited[i + 1][j] = false;
        }

        if (j - 1 >= 0 && !visited[i][j - 1]) {
            visited[i][j - 1] = true;
            recursion(arr, visited, i, j - 1, sum + arr[i][j - 1], depth + 1);
            visited[i][j - 1] = false;

        }

        if (j + 1 < arr[0].length && !visited[i][j + 1]) {
            visited[i][j + 1] = true;
            recursion(arr, visited, i, j + 1, sum + arr[i][j + 1], depth + 1);
            visited[i][j + 1] = false;
        }


    }
}