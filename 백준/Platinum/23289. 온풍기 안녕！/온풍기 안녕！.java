import java.io.*;
import java.util.*;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Machine {
    int y, x;
    int direction;

    Machine(int y, int x, int direction) {
        this.y = y;
        this.x = x;
        this.direction = direction;
    }
}

class Data {
    int y, x;
    int depth;

    Data(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    // 오 왼 위 아
    public static int[] dy = new int[]{0, 0, -1, 1};
    public static int[] dx = new int[]{1, -1, 0, 0};

    // 오른쪽인 경우
    // 위->오른쪽 , 오른쪽 , 아래-> 오른쪽

    // 왼쪽인 경우
    // 위->왼쪽, 왼쪽 , 아래->왼쪽

    // 위인 경우
    // 왼쪽-> 위 , 위 , 오른쪽 -> 위

    // 아래인 경우
    // 왼쪽->아래, 아래 , 오른쪽->아래

    public static int[][] directions = new int[][]{
            {2, 0, 0, 3, 0},
            {2, 1, 1, 3, 1},
            {1, 2, 2, 0, 2},
            {1, 3, 3, 0, 3}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int R, C, K;
        int[][] map;
        ArrayList<Machine> machines = new ArrayList<>();
        ArrayList<Point> checkPoints = new ArrayList<>();
        int W;
        boolean[][][] walls;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        walls = new boolean[4][R][C];

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[0].length; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == 0) {

                } else if (number == 5) {
                    checkPoints.add(new Point(i, j));
                } else {
                    machines.add(new Machine(i, j, number - 1));
                }
            }
        }

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                // y,x  <-> y-1,x
                walls[2][y][x] = true;
                walls[3][y - 1][x] = true;
            } else {
                // (y,x) (y,x+1)
                walls[0][y][x] = true;
                walls[1][y][x + 1] = true;
            }
        }

        ////////////////////////////////////////////////////////

        int answer = 0;


        while (true) {
//            printMap(map);


            // 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            function1(map, machines, walls);

            // 온도가 조절됨
            function2(map, walls);

            // 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            function3(map);

            // 초콜릿을 하나 먹는다.
            answer++;

            if (answer == 101)
                break;

            // 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
            if (function4(map, K, checkPoints))
                break;
        }

        System.out.println(answer);


    }

    public static void printMap(int[][] map) {
        System.out.println("---------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void function1(int[][] map, ArrayList<Machine> machines,
                                 boolean[][][] walls) {

        for (Machine machine : machines) {
            int[][] tmpMap = new int[map.length][map[0].length];

            int y = machine.y + dy[machine.direction];
            int x = machine.x + dx[machine.direction];
            tmpMap[y][x] = 5;

            ArrayList<Data> datas = new ArrayList<>();
            datas.add(new Data(y, x, 5));

            while (true) {

                if (datas.size() == 0)
                    break;

                if (datas.get(0).depth == 1)
                    break;

                ArrayList<Data> newDatas = new ArrayList<>();

                for (Data data : datas) {

                    int newY, newX;
                    int newY2, newX2;

                    newY = data.y + dy[directions[machine.direction][0]];
                    newX = data.x + dx[directions[machine.direction][0]];

                    if (!(newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)) {
                        if (!walls[directions[machine.direction][0]][data.y][data.x]) {
                            newY2 = newY + dy[directions[machine.direction][1]];
                            newX2 = newX + dx[directions[machine.direction][1]];

                            if (!(newY2 < 0 || newY2 >= map.length || newX2 < 0 || newX2 >= map[0].length)) {
                                if (!walls[directions[machine.direction][1]][newY][newX]) {

                                    tmpMap[newY2][newX2] = data.depth - 1;
                                    newDatas.add(new Data(newY2, newX2, data.depth - 1));
                                }
                            }

                        }
                    }

                    newY = data.y + dy[directions[machine.direction][2]];
                    newX = data.x + dx[directions[machine.direction][2]];

                    if (!(newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)) {
                        if (!walls[directions[machine.direction][2]][data.y][data.x]) {
                            tmpMap[newY][newX] = data.depth - 1;
                            newDatas.add(new Data(newY, newX, data.depth - 1));
                        }
                    }

                    newY = data.y + dy[directions[machine.direction][3]];
                    newX = data.x + dx[directions[machine.direction][3]];

                    if (!(newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)) {
                        if (!walls[directions[machine.direction][3]][data.y][data.x]) {
                            newY2 = newY + dy[directions[machine.direction][4]];
                            newX2 = newX + dx[directions[machine.direction][4]];

                            if (!(newY2 < 0 || newY2 >= map.length || newX2 < 0 || newX2 >= map[0].length)) {
                                if (!walls[directions[machine.direction][4]][newY][newX]) {

                                    tmpMap[newY2][newX2] = data.depth - 1;
                                    newDatas.add(new Data(newY2, newX2, data.depth - 1));
                                }
                            }

                        }
                    }


                }

                datas = newDatas;


            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] += tmpMap[i][j];
                }
            }

        }

    }

    public static void function2(int[][] map, boolean[][][] walls) {

        int[][] newMap = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {

                int sum = 0;
                for (int direction = 0; direction < 4; direction++) {

                    int newY = i + dy[direction];
                    int newX = j + dx[direction];

                    if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                        continue;

                    if (walls[direction][i][j])
                        continue;

                    if (map[i][j] > map[newY][newX]) {
                        sum += (map[i][j] - map[newY][newX]) / 4;
                        newMap[newY][newX] += (map[i][j] - map[newY][newX]) / 4;
                    }
                }

                newMap[i][j] = newMap[i][j] - sum;

            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }


    public static void function3(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || i == map.length - 1) {
                    if (map[i][j] > 0)
                        map[i][j]--;
                } else {
                    if (j == 0 || j == map[0].length - 1) {
                        if (map[i][j] > 0)
                            map[i][j]--;
                    }
                }
            }
        }
    }

    public static boolean function4(int[][] map, int K, ArrayList<Point> checkPoints) {
        for (Point point : checkPoints) {
            if (map[point.y][point.x] < K)
                return false;
        }

        return true;
    }
}