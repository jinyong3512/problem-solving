import java.io.*;
import java.util.*;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    public static int[] dy = new int[]{0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dx = new int[]{1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("R", 0);
        hashMap.put("L", 1);
        hashMap.put("B", 3);
        hashMap.put("T", 2);
        hashMap.put("RT", 6);
        hashMap.put("LT", 7);
        hashMap.put("RB", 4);
        hashMap.put("LB", 5);

        st = new StringTokenizer(br.readLine());

        int y,x;

        String str = st.nextToken();
        x = str.charAt(0)-'A';
        y = str.charAt(1)-'1';
        Point kingPoint = new Point(y,x);

//        System.out.println(kingPoint.y + " "+kingPoint.x);

        str = st.nextToken();
        x = str.charAt(0)-'A';
        y = str.charAt(1)-'1';
        Point rockPoint = new Point(y,x);

//        System.out.println(rockPoint.y + " " + rockPoint.x);

        int N = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            int direction = hashMap.get(line);

            int newY = kingPoint.y + dy[direction];
            int newX = kingPoint.x + dx[direction];

            if (newY >= 0 && newY < 8 && newX >= 0 && newX < 8) {

                if (newY == rockPoint.y && newX == rockPoint.x) {

                    if (rockPoint.y + dy[direction] >= 0 && rockPoint.y + dy[direction] < 8 && rockPoint.x + dx[direction] >= 0 && rockPoint.x + dx[direction] < 8) {
                        kingPoint.y += dy[direction];
                        kingPoint.x += dx[direction];
                        rockPoint.y += dy[direction];
                        rockPoint.x += dx[direction];
                    }

                }else{
                    kingPoint.y += dy[direction];
                    kingPoint.x += dx[direction];
                }
            }

        }

        sb.append((char) (kingPoint.x + 'A')).append((char) (kingPoint.y + '1')).append("\n")
                .append((char) (rockPoint.x + 'A')).append((char) (rockPoint.y + '1'));

        System.out.println(sb);

    }
}