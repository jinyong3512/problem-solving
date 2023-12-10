import java.io.*;
import java.util.*;

public class Main {

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 19:45 도전

        // 재현시는 전체를 의미
        // 격자의 각 칸은 구역이라고 부름
        // 구역을 다섯 개의 선거구로 나눠야 한다
        // 각 구역은 무조건 하나의 선거구 중 하나에 포함
        // 선거구는 구역을 적어도 하나 포함
        // 한 선거구에 포함되어 있는 구역은 모두 연결

        // 경계를 나눠서 board를 넘버링 하고
        // 그 board 넘버링을 읽으면서 arr 읽어서 값을 확인하자

        int N;
        int[][] A;

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        // 1 ≤ x < x+d1+d2 ≤ N
                        if (x + d1 + d2 > N)
                            continue;
                        //  1 ≤ y-d1 < y < y+d2 ≤ N
                        if (y - d1 <= 0)
                            continue;
                        //  1 ≤ y-d1 < y < y+d2 ≤ N
                        if (y + d2 > N)
                            continue;

                        int[][] board = new int[N + 1][N + 1];


                        //  1 ≤ r < x+d1, 1 ≤ c ≤ y
                        for (int r = 1; r < x + d1; r++) {
                            for (int c = 1; c <= y; c++) {
                                board[r][c] = 1;
                            }
                        }


                        //  1 ≤ r ≤ x+d2, y < c ≤ N
                        for (int r = 1; r <= x + d2; r++) {
                            for (int c = y + 1; c <= N; c++) {
                                board[r][c] = 2;
                            }
                        }


                        // x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
                        for (int r = x + d1; r <= N; r++) {
                            for (int c = 1; c < y - d1 + d2; c++) {
                                board[r][c] = 3;
                            }
                        }


                        // x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
                        for (int r = x + d2 + 1; r <= N; r++) {
                            for (int c = y - d1 + d2; c <= N; c++) {
                                board[r][c] = 4;
                            }
                        }


                        // (x, y), (x+1, y-1), ..., (x+d1, y-d1)
                        for (int d = 0; d <= d1; d++) {
                            board[x + d][y - d] = 5;
                        }


                        // (x, y), (x+1, y+1), ..., (x+d2, y+d2)
                        for (int d = 0; d <= d2; d++) {
                            board[x + d][y + d] = 5;
                        }


                        // (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
                        for (int d = 0; d <= d2; d++) {
                            board[x + d1 + d][y - d1 + d] = 5;
                        }


                        // (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
                        for (int d = 0; d <= d1; d++) {
                            board[x + d2 + d][y + d2 - d] = 5;
                        }


                        // 경계선 안에 색칠하기
                        for (int i = 1; i <= N; i++) {
                            int start = 0;
                            int end = N+1;

                            for (int j = 1; j <= N; j++) {
                                if (board[i][j] == 5) {
                                    start = j;
                                    break;
                                }
                            }

                            for (int j = N; j >= 1; j--) {
                                if (board[i][j] == 5) {
                                    end = j;
                                    break;
                                }
                            }

                            if(start == 0)
                                continue;

                            for (int j = start + 1; j <= end - 1; j++) {
                                board[i][j] = 5;
                            }

                        }


                        int[] sum = new int[6];
                        for (int i = 1; i <= N; i++) {
                            for (int j = 1; j <= N; j++) {
                                sum[board[i][j]] += A[i][j];
                            }
                        }

                        int max = Integer.MIN_VALUE;
                        int min = Integer.MAX_VALUE;

                        for (int i = 1; i <= 5; i++) {
                            max = Math.max(max, sum[i]);
                            min = Math.min(min, sum[i]);
                        }

                        answer = Math.min(answer, Math.abs(max - min));

                    }
                }
            }
        }

        System.out.println(answer);

    }
}