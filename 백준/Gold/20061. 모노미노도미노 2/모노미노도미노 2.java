import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;
        int[][] informations;

        n = Integer.parseInt(br.readLine());
        informations = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            informations[i][0] = t;
            informations[i][1] = y;
            informations[i][2] = x;
        }

        /////////////////////////////////////////////////////////////////////////
        // t = 1 : 1X1 (x,y)
        // t = 2 : 1x2 (x,y),(x,y+1)
        // t = 3 : 2x1 (x,y),(x+1,y)

        int answer = 0;

        boolean[][] blue = new boolean[4][6];
        boolean[][] green = new boolean[6][4];

        for (int nIndex = 0; nIndex < n; nIndex++) {

            int t = informations[nIndex][0];
            int y = informations[nIndex][1];
            int x = informations[nIndex][2];

            // green 으로 내리기
            if (t == 1) {
                int findY = -1;
                int findX = -1;
                for (int i = 0; i < 6; i++) {
                    if (!green[i][x]) {
                        findY = i;
                        findX = x;
                    }else{
                        break;
                    }
                }
                green[findY][findX] = true;
            } else if (t == 2) {
                int findY = -1;
                int findX = -1;
                for (int i = 0; i < 6; i++) {
                    if (!green[i][x] && !green[i][x + 1]) {
                        findY = i;
                        findX = x;
                    }else{
                        break;
                    }
                }
                green[findY][findX] = true;
                green[findY][findX + 1] = true;
            } else if (t == 3) {
                int findY = -1;
                int findX = -1;
                for (int i = 0; i < 5; i++) {
                    if (!green[i][x] && !green[i + 1][x]) {
                        findY = i;
                        findX = x;
                    }else{
                        break;
                    }
                }
                green[findY][findX] = true;
                green[findY + 1][findX] = true;
            }

            // blue로 오른쪽 보내기
            if (t == 1) {
                int findY = -1;
                int findX = -1;
                for (int j = 0; j < 6; j++) {
                    if (!blue[y][j]) {
                        findY = y;
                        findX = j;
                    }else{
                        break;
                    }
                }
                blue[findY][findX] = true;
            } else if (t == 2) {
                int findY = -1;
                int findX = -1;
                for (int j = 0; j < 5; j++) {
                    if (!blue[y][j] && !blue[y][j + 1]) {
                        findY = y;
                        findX = j;
                    }else{
                        break;
                    }
                }
                blue[findY][findX] = true;
                blue[findY][findX + 1] = true;
            } else if (t == 3) {
                int findY = -1;
                int findX = -1;
                for (int j = 0; j < 6; j++) {
                    if (!blue[y][j] && !blue[y + 1][j]) {
                        findY = y;
                        findX = j;
                    }else{
                        break;
                    }
                }
                blue[findY][findX] = true;
                blue[findY + 1][findX] = true;

//                if (nIndex == 4) {
//                    System.out.println("여기 들렸어 " + "find Y  = " + findY + " find X = " + findX);
//                }

            }
//
//            if (nIndex == 4) {
//
//                System.out.println("T = " + t + " y = " + y + "  x = " + x);
//
//                for (int i = 0; i < 4; i++) {
//                    for (int j = 0; j < 6; j++) {
//                        System.out.print(blue[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//            }


            // green 한 행 삭제 로직
            for (int i = 5; i >= 2; i--) {
                boolean allTrue = true;
                for (int j = 0; j < 4; j++) {
                    if (!green[i][j])
                        allTrue = false;
                }

                if (allTrue) {
                    answer += 1;
                    greenDelete(green, i);
                    i++;
                }
            }

            // blue 한 열 삭제 로직
            for (int j = 5; j >= 2; j--) {
                boolean allTrue = true;
                for (int i = 0; i < 4; i++) {
                    if (!blue[i][j])
                        allTrue = false;
                }

                if (allTrue) {
                    answer += 1;
                    blueDelete(blue, j);
                    j++;
                }
            }

            // green 0,1행 판단
            int count = 0;

            for (int j = 0; j < 4; j++) {
                if (green[0][j]) {
                    count += 1;
                    break;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (green[1][j]) {
                    count += 1;
                    break;
                }
            }

            for (int k = 0; k < count; k++) {
                greenDelete(green, 5);
            }

            // blue 0,1행 판단
            count = 0;

            for (int i = 0; i < 4; i++) {
                if (blue[i][0]) {
                    count += 1;
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (blue[i][1]) {
                    count += 1;
                    break;
                }
            }

            for (int k = 0; k < count; k++) {
                blueDelete(blue, 5);
            }


        }

        System.out.println(answer);

        int answer2 = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j])
                    answer2++;
                if (blue[j][i])
                    answer2++;
            }
        }
        System.out.println(answer2);

//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(green[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.print(blue[i][j] + " ");
//            }
//            System.out.println();
//        }


    }

    public static void greenDelete(boolean[][] green, int i) {
        for (int row = i; row > 0; row--) {
            for (int col = 0; col < 4; col++) {
                green[row][col] = green[row - 1][col];
            }
        }
        for (int col = 0; col < 4; col++) {
            green[0][col] = false;
        }
    }

    public static void blueDelete(boolean[][] blue, int j) {
        for (int col = j; col > 0; col--) {
            for (int row = 0; row < 4; row++) {
                blue[row][col] = blue[row][col - 1];
            }
        }

        for (int row = 0; row < 4; row++) {
            blue[row][0] = false;
        }
    }
}