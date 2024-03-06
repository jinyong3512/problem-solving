import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 21:30 시작

        // 가장 적은 어항에 각각 1마리 씩 넣어줌
        // 맨 왼쪽 어항을 바로 오른쪽 어항 머리 위에 올림
        // 쌓인 세로가 외톨이 가로 보다 크면 중지
        // 물고기 이동시키기
        // 바닥에 다시 놓기

        int N, K;
        int[] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////

        int answer = 0;
        while (true) {
            if (stop(arr, K)) {
                System.out.println(answer);
                break;
            }
            answer++;

            function1(arr);
            function2(arr);
            function3(arr);

        }
    }

    public static boolean stop(int[] arr, int K) {
        int maxNumber = Integer.MIN_VALUE;
        int minNumber = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            maxNumber = Math.max(maxNumber, arr[i]);
            minNumber = Math.min(minNumber, arr[i]);
        }

        if (maxNumber - minNumber <= K) {
            return true;
        } else
            return false;
    }

    public static void function1(int[] arr) {
        int minNumber = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            minNumber = Math.min(minNumber, arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            if (minNumber == arr[i]) {
                arr[i]++;
            }
        }
    }

    public static void function2(int[] arr) {
        int[][] newArr;

        newArr = new int[2][arr.length - 1];
        newArr[0][0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            newArr[1][i - 1] = arr[i];
        }
        int topJSize = 1;

        while (true) {

            if (newArr.length > newArr[0].length - topJSize)
                break;

            int[][] rotationArr = new int[newArr.length][topJSize];
            for (int i = 0; i < rotationArr.length; i++) {
                for (int j = 0; j < rotationArr[0].length; j++) {
                    rotationArr[i][j] = newArr[i][j];
                }
            }
            rotationArr = rotate(rotationArr);

            int[][] newNewArr = new int[rotationArr.length + 1][newArr[0].length - topJSize];


            for (int i = 0; i < rotationArr.length; i++) {
                for (int j = 0; j < rotationArr[0].length; j++) {
                    newNewArr[i][j] = rotationArr[i][j];
                }
            }
            for (int j = 0; j < newNewArr[0].length; j++) {
                newNewArr[newNewArr.length - 1][j] = newArr[newArr.length - 1][j + topJSize];
            }

            topJSize = rotationArr[0].length;

            newArr = newNewArr;
        }

        int[][] newNewArr = new int[newArr.length][newArr[0].length];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                newNewArr[i][j] = newArr[i][j];
            }
        }

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                if (newArr[i][j] != 0) {

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];

                        if (newY < 0 || newX < 0 || newY >= newArr.length || newX >= newArr[0].length)
                            continue;

                        if (newArr[newY][newX] == 0)
                            continue;

                        int gap = newArr[i][j] - newArr[newY][newX];
                        if (gap < 0)
                            continue;

                        int share = gap / 5;
                        if (share > 0) {
                            newNewArr[i][j] -= share;
                            newNewArr[newY][newX] += share;
                        }


                    }
                }
            }
        }

        newArr = newNewArr;

        int index = 0;
        for (int j = 0; j < newArr[0].length; j++) {
            for (int i = newArr.length - 1; i >= 0; i--) {
                if (newArr[i][j] != 0) {
                    arr[index] = newArr[i][j];
                    index++;
                }
            }
        }
    }

    public static int[][] rotate(int[][] arr) {
        int[][] newArr = new int[arr[0].length][arr.length];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                newArr[i][j] = arr[arr.length - 1 - j][i];
            }
        }
        return newArr;
    }

    public static void function3(int[] arr) {

        int[][] newArr;

        newArr = new int[1][arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newArr[0][i] = arr[i];
        }
        newArr = rotate(newArr);
        newArr = rotate(newArr);

        int[][] newNewArr = new int[2][arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newNewArr[0][i] = newArr[0][i];
            newNewArr[1][i] = arr[i + arr.length / 2];
        }
        newArr = newNewArr;

        int[][] splitNewArr = new int[newArr.length][newArr[0].length / 2];
        for (int i = 0; i < splitNewArr.length; i++) {
            for (int j = 0; j < splitNewArr[0].length; j++) {
                splitNewArr[i][j] = newArr[i][j];
            }
        }

        splitNewArr = rotate(splitNewArr);
        splitNewArr = rotate(splitNewArr);

        newNewArr = new int[4][splitNewArr[0].length];
        for (int i = 0; i < splitNewArr.length; i++) {
            for (int j = 0; j < splitNewArr[0].length; j++) {
                newNewArr[i][j] = splitNewArr[i][j];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length / 2; j++) {
                newNewArr[i + 2][j] = newArr[i][j + newArr[0].length / 2];
            }
        }

        newArr = newNewArr;

        newNewArr = new int[newArr.length][newArr[0].length];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                newNewArr[i][j] = newArr[i][j];
            }
        }

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                if (newArr[i][j] != 0) {

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];

                        if (newY < 0 || newX < 0 || newY >= newArr.length || newX >= newArr[0].length)
                            continue;

                        if (newArr[newY][newX] == 0)
                            continue;

                        int gap = newArr[i][j] - newArr[newY][newX];
                        if (gap < 0)
                            continue;

                        int share = gap / 5;
                        if (share > 0) {
                            newNewArr[i][j] -= share;
                            newNewArr[newY][newX] += share;
                        }


                    }
                }
            }
        }

        newArr = newNewArr;

        int index = 0;
        for (int j = 0; j < newArr[0].length; j++) {
            for (int i = newArr.length - 1; i >= 0; i--) {
                if (newArr[i][j] != 0) {
                    arr[index] = newArr[i][j];
                    index++;
                }
            }
        }

    }
}