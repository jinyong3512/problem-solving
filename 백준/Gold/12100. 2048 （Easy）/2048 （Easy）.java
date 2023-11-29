import java.io.*;
import java.util.*;

class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        // 4방향 모두 검사 해야 한다 5번까지 한 뒤에 최댓 값 구하기
        recursion(arr, 0);

        sb.append(answer);
        System.out.println(sb);
    }

    public static void recursion(int[][] arr, int depth) {

        if (depth == 5) {
            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    max = Math.max(max, arr[i][j]);
                }
            }
            answer = Math.max(answer, max);
            return;
        }

        int[][] new_arr;

        // 그걸 잘 못 생각 했다 중간에 0이 있어도 죽는구나

        // TODO : new_arr 위로 가기 먹이기
        new_arr = new int[arr.length][arr[0].length];
        for (int j = 0; j < arr[0].length; j++) {
            int new_arr_i = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][j] != 0) {
                    int sub_i = i + 1;
                    for (; sub_i < arr.length; sub_i++) {
                        if (arr[sub_i][j] == 0) {

                        } else if (arr[sub_i][j] == arr[i][j]) {
                            new_arr[new_arr_i][j] = arr[i][j] * 2;
                            new_arr_i++;
                            i = sub_i;
                            break;
                        } else {
                            new_arr[new_arr_i][j] = arr[i][j];
                            new_arr_i++;
                            i = sub_i - 1;
                            break;
                        }
                    }

                    if (sub_i == arr.length) {
                        new_arr[new_arr_i][j] = arr[i][j];
                        break;
                    }
                }
            }
        }

        recursion(new_arr, depth + 1);

        // TODO : new_arr 아래로 가기 먹이기
        new_arr = new int[arr.length][arr[0].length];
        for (int j = 0; j < arr[0].length; j++) {
            int new_arr_i = arr.length - 1;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i][j] != 0) {
                    int sub_i = i - 1;
                    for (; sub_i >= 0; sub_i--) {
                        if (arr[sub_i][j] == 0) {

                        } else if (arr[sub_i][j] == arr[i][j]) {
                            new_arr[new_arr_i][j] = arr[i][j] * 2;
                            new_arr_i--;
                            i = sub_i;
                            break;
                        } else {
                            new_arr[new_arr_i][j] = arr[i][j];
                            new_arr_i--;
                            i = sub_i + 1;
                            break;
                        }
                    }
                    if (sub_i == -1) {
                        new_arr[new_arr_i][j] = arr[i][j];
                        break;
                    }
                }

            }
        }

        recursion(new_arr, depth + 1);

        // TODO : new_arr 왼쪽으로 가기 먹이기
        new_arr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            int new_arr_j = 0;
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    int sub_j = j + 1;
                    for (; sub_j < arr[0].length; sub_j++) {
                        if (arr[i][sub_j] == 0) {

                        } else if (arr[i][sub_j] == arr[i][j]) {
                            new_arr[i][new_arr_j] = arr[i][j] * 2;
                            new_arr_j++;
                            j = sub_j;
                            break;
                        } else {
                            new_arr[i][new_arr_j] = arr[i][j];
                            new_arr_j++;
                            j = sub_j - 1;
                            break;
                        }
                    }

                    if (sub_j == arr[0].length) {
                        new_arr[i][new_arr_j] = arr[i][j];
                        break;
                    }

                }
            }
        }

        recursion(new_arr, depth + 1);

        // TODO : new_arr 오른쪽으로 가기 먹이기
        new_arr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            int new_arr_j = arr[0].length - 1;
            for (int j = arr[0].length - 1; j >= 0; j--) {
                if (arr[i][j] != 0) {
                    int sub_j = j - 1;
                    for (; sub_j >= 0; sub_j--) {
                        if (arr[i][sub_j] == 0) {

                        } else if (arr[i][sub_j] == arr[i][j]) {
                            new_arr[i][new_arr_j] = arr[i][j] * 2;
                            new_arr_j--;
                            j = sub_j;
                            break;
                        } else {
                            new_arr[i][new_arr_j] = arr[i][j];
                            new_arr_j--;
                            j = sub_j + 1;
                            break;
                        }
                    }
                    if (sub_j == -1) {
                        new_arr[i][new_arr_j] = arr[i][j];
                        break;
                    }

                }
            }
        }

        recursion(new_arr, depth + 1);


    }
}