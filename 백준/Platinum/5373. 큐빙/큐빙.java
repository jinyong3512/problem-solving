import java.io.*;
import java.util.*;

class Block {
    int top;
    int bottom;
    int front;
    int back;
    int left;
    int right;

    Block(int top, int bottom, int front, int back, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
    }
}

public class Main {

    public static int WHITE = 1;
    public static int YELLOW = 2;
    public static int RED = 3;
    public static int ORANGE = 4;
    public static int GREEN = 5;
    public static int BLUE = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            Block[][][] dice = new Block[3][3][3];

            dice[0][0][0] = new Block(0, YELLOW, 0, ORANGE, GREEN, 0);
            dice[0][0][1] = new Block(0, YELLOW, 0, ORANGE, 0, 0);
            dice[0][0][2] = new Block(0, YELLOW, 0, ORANGE, 0, BLUE);

            dice[0][1][0] = new Block(0, YELLOW, 0, 0, GREEN, 0);
            dice[0][1][1] = new Block(0, YELLOW, 0, 0, 0, 0);
            dice[0][1][2] = new Block(0, YELLOW, 0, 0, 0, BLUE);

            dice[0][2][0] = new Block(0, YELLOW, RED, 0, GREEN, 0);
            dice[0][2][1] = new Block(0, YELLOW, RED, 0, 0, 0);
            dice[0][2][2] = new Block(0, YELLOW, RED, 0, 0, BLUE);

            dice[1][0][0] = new Block(0, 0, 0, ORANGE, GREEN, 0);
            dice[1][0][1] = new Block(0, 0, 0, ORANGE, 0, 0);
            dice[1][0][2] = new Block(0, 0, 0, ORANGE, 0, BLUE);

            dice[1][1][0] = new Block(0, 0, 0, 0, GREEN, 0);
            dice[1][1][1] = new Block(0, 0, 0, 0, 0, 0);
            dice[1][1][2] = new Block(0, 0, 0, 0, 0, BLUE);

            dice[1][2][0] = new Block(0, 0, RED, 0, GREEN, 0);
            dice[1][2][1] = new Block(0, 0, RED, 0, 0, 0);
            dice[1][2][2] = new Block(0, 0, RED, 0, 0, BLUE);

            dice[2][0][0] = new Block(WHITE, 0, 0, ORANGE, GREEN, 0);
            dice[2][0][1] = new Block(WHITE, 0, 0, ORANGE, 0, 0);
            dice[2][0][2] = new Block(WHITE, 0, 0, ORANGE, 0, BLUE);

            dice[2][1][0] = new Block(WHITE, 0, 0, 0, GREEN, 0);
            dice[2][1][1] = new Block(WHITE, 0, 0, 0, 0, 0);
            dice[2][1][2] = new Block(WHITE, 0, 0, 0, 0, BLUE);

            dice[2][2][0] = new Block(WHITE, 0, RED, 0, GREEN, 0);
            dice[2][2][1] = new Block(WHITE, 0, RED, 0, 0, 0);
            dice[2][2][2] = new Block(WHITE, 0, RED, 0, 0, BLUE);

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                String order = st.nextToken();

                if (order.charAt(0) == 'U') {
                    if (order.charAt(1) == '+') {
                        DiceRotateBack(dice, "U");
                    } else {
                        DiceRotateBack(dice, "U");
                        DiceRotateBack(dice, "U");
                        DiceRotateBack(dice, "U");
                    }
                } else if (order.charAt(0) == 'D') {
                    if (order.charAt(1) == '+') {
                        DiceRotateBack(dice, "D");
                        DiceRotateBack(dice, "D");
                        DiceRotateBack(dice, "D");
                    } else {
                        DiceRotateBack(dice, "D");
                    }
                } else if (order.charAt(0) == 'L') {
                    if (order.charAt(1) == '+') {
                        DiceRotateFront(dice, "L");
                    } else {
                        DiceRotateFront(dice, "L");
                        DiceRotateFront(dice, "L");
                        DiceRotateFront(dice, "L");
                    }
                } else if (order.charAt(0) == 'R') {
                    if (order.charAt(1) == '+') {
                        DiceRotateFront(dice, "R");
                        DiceRotateFront(dice, "R");
                        DiceRotateFront(dice, "R");
                    } else {
                        DiceRotateFront(dice, "R");
                    }
                } else if (order.charAt(0) == 'F') {
                    if (order.charAt(1) == '+') {
                        DiceRotateRight(dice, "F");
                    } else {
                        DiceRotateRight(dice, "F");
                        DiceRotateRight(dice, "F");
                        DiceRotateRight(dice, "F");
                    }
                } else if (order.charAt(0) == 'B') {
                    if (order.charAt(1) == '+') {
                        DiceRotateRight(dice, "B");
                        DiceRotateRight(dice, "B");
                        DiceRotateRight(dice, "B");
                    } else {
                        DiceRotateRight(dice, "B");
                    }
                } else {
                    System.out.println("버그");
                }

//                System.out.println(order);
//                printU(dice);
//                System.out.println("-------------");

            }

            printU(dice);

        }

    }

    public static void printU(Block[][][] dice) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (dice[2][i][j].top == 1) {
                    System.out.print("w");
                } else if (dice[2][i][j].top == 2) {
                    System.out.print("y");
                } else if (dice[2][i][j].top == 3) {
                    System.out.print("r");
                } else if (dice[2][i][j].top == 4) {
                    System.out.print("o");
                } else if (dice[2][i][j].top == 5) {
                    System.out.print("g");
                } else if (dice[2][i][j].top == 6) {
                    System.out.print("b");
                } else {
                    System.out.print("버그");
                }
            }
            System.out.println();
        }
    }

    public static void DiceRotateFront(Block[][][] dice, String where) {
        Block tmp;
        int j = -1;

        if (where.equals("L"))
            j = 0;
        else if (where.equals("R"))
            j = 2;

        tmp = dice[2][2][j];
        dice[2][2][j] = dice[2][0][j];
        dice[2][0][j] = dice[0][0][j];
        dice[0][0][j] = dice[0][2][j];
        dice[0][2][j] = tmp;
        BlockRotateFront(dice[2][2][j]);
        BlockRotateFront(dice[2][0][j]);
        BlockRotateFront(dice[0][0][j]);
        BlockRotateFront(dice[0][2][j]);

        tmp = dice[2][1][j];
        dice[2][1][j] = dice[1][0][j];
        dice[1][0][j] = dice[0][1][j];
        dice[0][1][j] = dice[1][2][j];
        dice[1][2][j] = tmp;
        BlockRotateFront(dice[2][1][j]);
        BlockRotateFront(dice[1][0][j]);
        BlockRotateFront(dice[0][1][j]);
        BlockRotateFront(dice[1][2][j]);
    }

    public static void BlockRotateFront(Block block) {
        int tmp = block.top;
        block.top = block.back;
        block.back = block.bottom;
        block.bottom = block.front;
        block.front = tmp;
    }

    public static void DiceRotateRight(Block[][][] dice, String where) {
        Block tmp;
        int i = -1;

        if (where.equals("F"))
            i = 2;
        else if (where.equals("B"))
            i = 0;

        tmp = dice[2][i][0];
        dice[2][i][0] = dice[0][i][0];
        dice[0][i][0] = dice[0][i][2];
        dice[0][i][2] = dice[2][i][2];
        dice[2][i][2] = tmp;
        BlockRotateRight(dice[2][i][0]);
        BlockRotateRight(dice[0][i][0]);
        BlockRotateRight(dice[0][i][2]);
        BlockRotateRight(dice[2][i][2]);

        tmp = dice[2][i][1];
        dice[2][i][1] = dice[1][i][0];
        dice[1][i][0] = dice[0][i][1];
        dice[0][i][1] = dice[1][i][2];
        dice[1][i][2] = tmp;
        BlockRotateRight(dice[2][i][1]);
        BlockRotateRight(dice[1][i][0]);
        BlockRotateRight(dice[0][i][1]);
        BlockRotateRight(dice[1][i][2]);
    }

    public static void BlockRotateRight(Block block) {
        int tmp = block.top;
        block.top = block.left;
        block.left = block.bottom;
        block.bottom = block.right;
        block.right = tmp;
    }

    public static void DiceRotateBack(Block[][][] dice, String where) {
        Block tmp;
        int k = -1;

        if (where.equals("U"))
            k = 2;
        else if (where.equals("D"))
            k = 0;

        tmp = dice[k][0][1];
        dice[k][0][1] = dice[k][1][0];
        dice[k][1][0] = dice[k][2][1];
        dice[k][2][1] = dice[k][1][2];
        dice[k][1][2] = tmp;
        BlockRotateBack(dice[k][0][1]);
        BlockRotateBack(dice[k][1][0]);
        BlockRotateBack(dice[k][2][1]);
        BlockRotateBack(dice[k][1][2]);

        tmp = dice[k][0][0];
        dice[k][0][0] = dice[k][2][0];
        dice[k][2][0] = dice[k][2][2];
        dice[k][2][2] = dice[k][0][2];
        dice[k][0][2] = tmp;
        BlockRotateBack(dice[k][0][0]);
        BlockRotateBack(dice[k][2][0]);
        BlockRotateBack(dice[k][2][2]);
        BlockRotateBack(dice[k][0][2]);
    }

    public static void BlockRotateBack(Block block) {
        int tmp = block.left;
        block.left = block.front;
        block.front = block.right;
        block.right = block.back;
        block.back = tmp;
    }
}