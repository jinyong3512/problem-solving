import java.io.*;
import java.util.*;

class Smell {
    int ownerNumber;
    int time;
}

class Shark {
    int number;
    int direction;
}

public class Main {

    // 1234 위 아래 왼쪽 오른쪽
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1 ~ M 상어 번호
        // 1번은 강력해서 모두 쫓아냄

        // 1. 자신의 위치에 냄새를 뿌림
        // 2. 1초마다 상어가 동시에 상하좌우로 이동
        // 3. 자신의 냄새를 그 칸에 뿌림
        // * 냄새는 k번 이동하고 나면 사라진다.
        // 상어가 이동은 아무 냄새가 없는 칸으로
        // 자신의 냄새가 있는 칸의 방향으로 잡는다. 이때도 여러가지면
        // 이동한 방향이 바라보는 방향이 된다
        // 제일 작은 번호를 가진 상어만 그 격자에 남습니다


        // 냄새 뿌리기
        // 이동하기

        int N, M, k;
        Smell[][] smells;
        Shark[][] sharks;
        int[][][] priorityDirection;

        ////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        smells = new Smell[N][N];
        sharks = new Shark[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number != 0) {
                    sharks[i][j] = new Shark();
                    sharks[i][j].number = number;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (sharks[y][x] != null && sharks[y][x].number == i) {
                        sharks[y][x].direction = Integer.parseInt(st.nextToken()) - 1;
                    }
                }
            }
        }

        priorityDirection = new int[M + 1][4][4];

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int z = 0; z < 4; z++) {
                    priorityDirection[i][j][z] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }


//        printSmells(smells);
//        printSharks(sharks);
//        printPriorityDirection(priorityDirection);

        int answer = 0;

        while (true) {
            // 냄새 뿌리기
            for (int i = 0; i < sharks.length; i++) {
                for (int j = 0; j < sharks[0].length; j++) {
                    if (sharks[i][j] != null) {
                        smells[i][j] = new Smell();
                        smells[i][j].ownerNumber = sharks[i][j].number;
                        smells[i][j].time = k;
                    }
                }
            }

            // 상어 이동하기
            Shark[][] newSharks = new Shark[sharks.length][sharks[0].length];
            for (int i = 0; i < sharks.length; i++) {
                for (int j = 0; j < sharks[0].length; j++) {
                    if (sharks[i][j] != null) {

                        // 아무 냄새가 없는 칸 찾기
                        int countGoTrue = 0;
                        boolean[] go = new boolean[]{false, false, false, false};
                        for (int direction = 0; direction < 4; direction++) {
                            int newY = i + dy[direction];
                            int newX = j + dx[direction];

                            if (newY < 0 || newY >= N || newX < 0 || newX >= N)
                                continue;

                            if (smells[newY][newX] == null) {
                                go[direction] = true;
                                countGoTrue += 1;
                            }
                        }

                        if (countGoTrue == 0) {
                            // 자신의 냄새 칸 찾기
                            for (int direction = 0; direction < 4; direction++) {
                                int newY = i + dy[direction];
                                int newX = j + dx[direction];

                                if (newY < 0 || newY >= N || newX < 0 || newX >= N)
                                    continue;

                                if (smells[newY][newX].ownerNumber == sharks[i][j].number) {
                                    go[direction] = true;
                                    countGoTrue += 1;
                                }
                            }
                        }

                        int newY = -1;
                        int newX = -1;
                        int newDirection = -1;

                        if (countGoTrue == 0) {

                            newY = i;
                            newX = j;
                            newDirection = sharks[i][j].direction;

                        } else if (countGoTrue == 1) {

                            int direction = 0;
                            for (; direction < 4; direction++) {
                                if (go[direction]) {
                                    break;
                                }
                            }
                            newY = i + dy[direction];
                            newX = j + dx[direction];
                            newDirection = direction;

                        } else {
                            int index = 0;
                            for (; index < 4; index++) {
                                if (go[priorityDirection[sharks[i][j].number][sharks[i][j].direction][index]]) {
                                    break;
                                }
                            }
                            newDirection = priorityDirection[sharks[i][j].number][sharks[i][j].direction][index];
                            newY = i + dy[newDirection];
                            newX = j + dx[newDirection];
                        }

                        if (newSharks[newY][newX] == null) {
                            newSharks[newY][newX] = new Shark();
                            newSharks[newY][newX].number = sharks[i][j].number;
                            newSharks[newY][newX].direction = newDirection;
                        } else {
                            if (sharks[i][j].number < newSharks[newY][newX].number) {
                                newSharks[newY][newX].number = sharks[i][j].number;
                                newSharks[newY][newX].direction = newDirection;
                            }
                        }

                    }
                }
            }

            sharks = newSharks;

            // 냄새 1씩 제거하기
            for (int i = 0; i < smells.length; i++) {
                for (int j = 0; j < smells[0].length; j++) {
                    if (smells[i][j] != null) {
                        smells[i][j].time--;
                        if (smells[i][j].time == 0) {
                            smells[i][j] = null;
                        }
                    }
                }
            }

            answer++;

            if (answer > 1000) {
                sb.append("-1");
                break;
            }

            // 상어가 한마리면 스탑
            int count = 0;
            for (int i = 0; i < sharks.length; i++) {
                for (int j = 0; j < sharks[0].length; j++) {
                    if (sharks[i][j] != null)
                        count++;
                }
            }
            if (count == 1)
                break;

        }

        if (sb.length() == 0)
            sb.append(answer);

        System.out.println(sb);


    }

    public static void printSmells(Smell[][] smells) {
        for (int i = 0; i < smells.length; i++) {
            for (int j = 0; j < smells[0].length; j++) {
                System.out.print(smells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printSharks(Shark[][] sharks) {
        for (int i = 0; i < sharks.length; i++) {
            for (int j = 0; j < sharks[0].length; j++) {
                System.out.print(sharks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printPriorityDirection(int[][][] priroityDirection) {
        for (int i = 1; i < priroityDirection.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int z = 0; z < 4; z++) {
                    System.out.print(priroityDirection[i][j][z] + " ");
                }
                System.out.println();
            }
            System.out.println("------");
        }
    }
}