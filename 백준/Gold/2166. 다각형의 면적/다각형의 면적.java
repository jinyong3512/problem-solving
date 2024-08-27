import java.io.*;
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        points[N] = new Point(points[0].x, points[0].y);

        /////////////////////////////////////////////

        long sum = 0;

        for (int i = 0; i < N; i++) {
            sum += (long)points[i].x * points[i + 1].y;
        }
        for (int i = 0; i < N; i++) {
            sum -= (long)points[i].y * points[i + 1].x;
        }

        System.out.printf("%.1f", Math.abs(sum / 2.0));

    }
}