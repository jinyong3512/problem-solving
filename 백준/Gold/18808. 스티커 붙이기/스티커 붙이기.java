import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int K;
        boolean[][] board;
        int R, C;
        ArrayList<boolean[][]> stickers = new ArrayList<>();
        boolean[][] sticker;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            sticker = new boolean[R][C];

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    if (st.nextToken().equals("0")) {
                        sticker[i][j] = false;
                    } else {
                        sticker[i][j] = true;
                    }
                }
            }

            stickers.add(sticker);
        }

        ///////////////////////////////////////////////////////////////////////

        for (int stickersIndex = 0; stickersIndex < stickers.size(); stickersIndex++) {

//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[0].length; j++) {
//                    if (board[i][j])
//                        System.out.print("1");
//                    else
//                        System.out.print("0");
//                }
//                System.out.println();
//            }

            sticker = stickers.get(stickersIndex);

            for (int rotationCount = 0; rotationCount < 4; rotationCount++) {
                if (simulation(board, sticker)) {
                    break;
                } else {
                    sticker = rotation(sticker);
                }
            }

        }

        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j])
                    answer++;
            }
        }
        System.out.println(answer);


    }

    public static boolean simulation(boolean[][] board, boolean[][] sticker) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                boolean can = true;

                for (int y = 0; y < sticker.length; y++) {
                    for (int x = 0; x < sticker[0].length; x++) {
                        int newY = i + y;
                        int newX = j + x;

                        if (newY >= board.length || newX >= board[0].length) {
                            can = false;
                            break;
                        }

                        if (board[newY][newX]) {
                            if (sticker[y][x]) {
                                can = false;
                                break;
                            }
                        }
                    }
                }

                if (can) {

                    for (int y = 0; y < sticker.length; y++) {
                        for (int x = 0; x < sticker[0].length; x++) {
                            int newY = i + y;
                            int newX = j + x;

                            if (sticker[y][x])
                                board[newY][newX] = true;
                        }
                    }

                    return true;
                }

            }
        }


        return false;
    }

    public static boolean[][] rotation(boolean[][] sticker) {
        boolean[][] newSticker = new boolean[sticker[0].length][sticker.length];

        for (int i = 0; i < newSticker.length; i++) {
            for (int j = 0; j < newSticker[0].length; j++) {
                newSticker[i][j] = sticker[sticker.length - 1 - j][i];
            }
        }

        return newSticker;
    }

}