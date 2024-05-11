import java.io.*;
import java.util.*;

class Shark {
    int y, x;
    int answer;
    int direction;

    Shark(int y, int x, int answer, int direction) {
        this.y = y;
        this.x = x;
        this.answer = answer;
        this.direction = direction;
    }
}

class Fish {
    int number;
    int direction;

    Fish(int number, int direction) {
        this.number = number;
        this.direction = direction;
    }
}

public class Main {

    public static int answer;

    public static int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        // 물고기 이동 반시계 45도 회전하면서 찾기
        // 가능:
        // 빈칸
        // 다른 물고기: 서로 위치르 바꾼다
        // 불가능:
        // 상어가 있거나 경게를 넘는 칸

        // 상어의 이동 방향의 여러 칸으로 이동
        // 가능: 물고기가 있는 칸 -> 그 방향을 가지게 됌
        // 불가능: 물고기가 없는 칸
        // 이동할 수 있는 칸이 없으면 집에 감

        Fish[][] fishes = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                fishes[i][j] = new Fish(number, direction - 1);
            }
        }

        Shark shark = new Shark(0, 0, fishes[0][0].number, fishes[0][0].direction);
        fishes[0][0] = null;

        answer = 0;
        simulation(shark, fishes);

        System.out.println(answer);

    }

    public static void simulation(Shark shark, Fish[][] fishes) {
        // 물고기 이동
        fishesMove(shark, fishes);
        // 상어 이동
        Shark newShark;
        Fish[][] newFishes;

        boolean can = false;
        for (int depth = 1; depth <= 4; depth++) {
            int newY = shark.y + dy[shark.direction] * depth;
            int newX = shark.x + dx[shark.direction] * depth;

            if (newY < 0 || newY >= 4 || newX < 0 || newX >= 4)
                continue;

            if (fishes[newY][newX] == null)
                continue;

            newShark = new Shark(newY, newX, shark.answer + fishes[newY][newX].number, fishes[newY][newX].direction);
            newFishes = new Fish[4][4];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (fishes[i][j] != null)
                        newFishes[i][j] = new Fish(fishes[i][j].number, fishes[i][j].direction);
                }
            }
            newFishes[newY][newX] = null;
            can = true;

            simulation(newShark, newFishes);

        }

        if (!can) {
            answer = Math.max(answer, shark.answer);
        }
    }

    public static void fishesMove(Shark shark, Fish[][] fishes) {
        for (int number = 1; number <= 16; number++) {

            int findFishY = -1;
            int findFishX = -1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (fishes[i][j] != null && fishes[i][j].number == number) {
                        findFishY = i;
                        findFishX = j;
                    }
                }
            }

            if (findFishY == -1)
                continue;


            for (int rotationCount = 0; rotationCount < 8; rotationCount++) {

                int fishDirection = (fishes[findFishY][findFishX].direction + rotationCount) % 8;

                int newY = findFishY + dy[fishDirection];
                int newX = findFishX + dx[fishDirection];

                if (newY < 0 || newY >= 4 || newX < 0 || newX >= 4)
                    continue;

                if (shark.y == newY && shark.x == newX)
                    continue;

                // 성공
                Fish tmp = fishes[newY][newX];
                fishes[newY][newX] = fishes[findFishY][findFishX];
                fishes[findFishY][findFishX] = tmp;
                fishes[newY][newX].direction = fishDirection;
                break;

            }
        }
    }
}
