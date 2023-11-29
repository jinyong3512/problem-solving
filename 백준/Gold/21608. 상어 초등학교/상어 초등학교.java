import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        //////////////////////////////////////////////////////////////////////////////

        N = Integer.parseInt(br.readLine());
        arr = new int[N * N][5];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        ///////////////////////////////////////////////////////////////////////////////

        int[][] table = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                table[i][j] = -1;
            }
        }

        table[1][1] = arr[0][0];

        // 한명씩 배치하자
        for (int i = 1; i < N * N; i++) {

            int[][] like = new int[N][N];
            int[][] empty = new int[N][N];

            // 한자리씩 검사
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {

                    // 위로
                    if (y - 1 >= 0) {
                        // 비어 있으면
                        if (table[y - 1][x] == -1)
                            empty[y][x]++;
                        else {
                            for (int j = 1; j <= 4; j++) {
                                if (arr[i][j] == table[y - 1][x]) {
                                    like[y][x]++;
                                    break;
                                }
                            }
                        }
                    }

                    // 아래로
                    if (y + 1 < N) {
                        // 비어 있으면
                        if (table[y + 1][x] == -1)
                            empty[y][x]++;
                        else {
                            for (int j = 1; j <= 4; j++) {
                                if (arr[i][j] == table[y + 1][x]) {
                                    like[y][x]++;
                                    break;
                                }
                            }
                        }
                    }

                    // 왼쪽으로
                    if (x - 1 >= 0) {
                        // 비어 있으면
                        if (table[y][x - 1] == -1)
                            empty[y][x]++;
                        else {
                            for (int j = 1; j <= 4; j++) {
                                if (arr[i][j] == table[y][x - 1]) {
                                    like[y][x]++;
                                    break;
                                }
                            }
                        }
                    }

                    // 왼쪽으로
                    if (x + 1 < N) {
                        // 비어 있으면
                        if (table[y][x + 1] == -1)
                            empty[y][x]++;
                        else {
                            for (int j = 1; j <= 4; j++) {
                                if (arr[i][j] == table[y][x + 1]) {
                                    like[y][x]++;
                                    break;
                                }
                            }
                        }
                    }

                }
            }

            int findY = -1;
            int findX = -1;
            int maxLike = -1;
            int maxEmpty = -1;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (table[y][x] == -1) {
                        if (maxLike < like[y][x]) {
                            findY = y;
                            findX = x;
                            maxLike = like[y][x];
                            maxEmpty = empty[y][x];
                        } else if (maxLike == like[y][x] && maxEmpty < empty[y][x]) {
                            findY = y;
                            findX = x;
                            maxEmpty = empty[y][x];
                        }
                    }
                }
            }

            table[findY][findX] = arr[i][0];


        }

//        for (int i = 0; i < N; i++) {
//
//            for (int j = 0; j < N; j++) {
//                System.out.print(table[i][j]+" ");
//            }
//            System.out.println("");
//        }


        long score = 0;
        for (int i = 0; i < N * N; i++) {

            int findY = -1;
            int findX = -1;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (table[y][x] == arr[i][0]) {
                        findY = y;
                        findX = x;
                        break;
                    }
                }
            }

            int count = 0;

            for (int j = 1; j < 5; j++) {
                if (findY - 1 >= 0 && table[findY-1][findX]==arr[i][j])
                    count++;

                if (findY +1 < N && table[findY+1][findX]==arr[i][j])
                    count++;

                if(findX-1>=0 && table[findY][findX-1] == arr[i][j])
                    count++;

                if(findX+1<N && table[findY][findX+1]==arr[i][j])
                    count++;
            }

            score = (long) (score + Math.pow(10,count-1));
        }

        System.out.println(score);

    }
}