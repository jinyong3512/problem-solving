import java.io.*;
import java.util.*;

class Fish {
    int number;
    int direction;

    Fish(int number, int direction) {
        this.number = number;
        this.direction = direction;
    }
}

class Shark {
    int y, x, score, direction;

    Shark(int y, int x, int score, int direction) {
        this.y = y;
        this.x = x;
        this.score = score;
        this.direction = direction;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // Fish[][] board로 만들 경우엔 1번 물고기를 직접 계속 찾아야 함
        // ArrayList<Fish> fishes 로 만들 경우에는 fishes 를 돌면서 내 오른쪽 위가 누군지 찾아야 함

        Fish[][] fishes = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                fishes[i][j] = new Fish(number, direction);
            }
        }

        Shark shark = new Shark(0, 0, fishes[0][0].number, fishes[0][0].direction);
        fishes[0][0] = null;

        recursion(fishes, shark);

        System.out.println(answer);
    }

    public static void recursion(Fish[][] fishes, Shark shark) {

        answer = Math.max(answer, shark.score);

        // 물고기 이동해!
        for (int fishNumber = 1; fishNumber <= 16; fishNumber++) {
            int fishY = -1;
            int fishX = -1;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (fishes[i][j]!=null && fishes[i][j].number == fishNumber) {
                        fishY = i;
                        fishX = j;
                    }
                }
            }

            if (fishY == -1) {
                continue;
            }

            // 이동 할 수 있는 칸 빈칸 , 다른물고기 칸,
            // 이동 할 수 없는 칸상어, 공간 넘는 칸
            for (int direction = 0; direction < 8; direction++) {
                int newFishY = fishY + dy[(fishes[fishY][fishX].direction + direction) % 8];
                int newFishX = fishX + dx[(fishes[fishY][fishX].direction + direction) % 8];

                if (newFishY < 0 || newFishY >= 4 || newFishX < 0 || newFishX >= 4)
                    continue;
                if (newFishY == shark.y && newFishX == shark.x)
                    continue;

                Fish tmpFish = fishes[newFishY][newFishX];
                fishes[newFishY][newFishX] = fishes[fishY][fishX];
                fishes[fishY][fishX] = tmpFish;

                fishes[newFishY][newFishX].direction = (fishes[newFishY][newFishX].direction + direction) % 8;
                break;
            }

        }

        // 상어 이동해!
        int distance = 0;
        while (true) {
            distance += 1;
            int newSharkY = shark.y + dy[shark.direction] * distance;
            int newSharkX = shark.x + dx[shark.direction] * distance;

            if (newSharkY < 0 || newSharkY >= 4 || newSharkX < 0 || newSharkX >= 4)
                break;

            if (fishes[newSharkY][newSharkX] == null)
                continue;

            Fish[][] newFishes = new Fish[4][4];
            for(int i =0 ; i < 4 ; i++){
                for(int j = 0 ; j < 4 ; j++){
                    if(fishes[i][j]==null)
                        newFishes[i][j]=null;
                    else{
                        newFishes[i][j] = new Fish(fishes[i][j].number,fishes[i][j].direction);
                    }
                }
            }

            Shark newShark = new Shark(newSharkY,newSharkX,shark.score+newFishes[newSharkY][newSharkX].number,newFishes[newSharkY][newSharkX].direction);
            newFishes[newSharkY][newSharkX]=null;
            recursion(newFishes,newShark);

        }
    }
}