import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{0, 1, 1, 1};
    public static int[] dx = new int[]{1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별하시오.

        while (true) {

            String line = br.readLine();
            if (line.equals("end"))
                break;

            char[][] arr = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = line.charAt(i * 3 + j);
                }
            }

            // 가로,세로,대각선 3칸 끝 , 가득차도 끝
            int countX = 0;
            int countO = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == 'X')
                        countX++;
                    else if (arr[i][j] == 'O')
                        countO++;
                }
            }

            HashMap<Character, Integer> hashMap = new HashMap<>();

            for (int i = 0; i < 3; i++) {
                if (check(arr, i, 0, 0))
                    hashMap.put(arr[i][0], hashMap.getOrDefault(arr[i][0], 0) + 1);
            }

            for (int j = 0; j < 3; j++) {
                if (check(arr, 0, j, 1))
                    hashMap.put(arr[0][j], hashMap.getOrDefault(arr[0][j], 0) + 1);
            }

            if (check(arr, 0, 0, 2))
                hashMap.put(arr[0][0], hashMap.getOrDefault(arr[0][0], 0) + 1);
            if (check(arr, 0, 2, 3))
                hashMap.put(arr[0][2], hashMap.getOrDefault(arr[0][2], 0) + 1);

            // X가 이긴 상태여야 함 or 둘다 이기지 못했는데 꽉찬 상태
            if (countX == countO + 1) {

                if (hashMap.containsKey('X') && !hashMap.containsKey('O'))
                    sb.append("valid").append("\n");
                else if (!hashMap.containsKey('X') && !hashMap.containsKey('O') && countX == 5)
                    sb.append("valid").append("\n");
                else
                    sb.append("invalid").append("\n");
            }
            // O가 이긴 상태여야 함
            else if (countX == countO) {

                if (!hashMap.containsKey('X') && hashMap.containsKey('O'))
                    sb.append("valid").append("\n");
                else
                    sb.append("invalid").append("\n");

            } else {
                sb.append("invalid").append("\n");
            }
        }

        System.out.println(sb);
    }

    public static boolean check(char[][] arr, int i, int j, int direction) {

        for (int depth = 0; depth < 2; depth++) {

            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (arr[i][j] != arr[newY][newX])
                return false;

            i = newY;
            j = newX;
        }

        return true;
    }
}
