import java.util.*;
import java.io.*;

//75,060 kb 메모리 439 ms 실행시간

class Solution {
	public static void main(String args[]) throws Exception {
		// Scanner 클래스 로드
		Scanner sc = new Scanner(System.in);
		// 출력 클래스 로드
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수입력 받기
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 배열 크기 N 받기
			int N = sc.nextInt();
			// 배열 선언후 초기화
			int[][] arr = new int[N][N];

			// 배열 입력 받기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();
			}

			/////////////////////////////////////////////////

			// 해당 지점에서 최댓값 기억하기
			int[][] memorization = new int[N][N];

			// 모든 점에 대해 dfs 실행
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					memorization[i][j] = dfs(arr, i, j, memorization);
				}
			}

			int max = 0;
			int max_arr_value = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < memorization[i][j]) {
						max = memorization[i][j];
						max_arr_value = arr[i][j];
					} else if (max == memorization[i][j] && max_arr_value > arr[i][j])
						max_arr_value = arr[i][j];
				}
			}
			sb.append("#").append(test_case).append(" ").append(max_arr_value).append(" ").append(max).append("\n");

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++)
//					System.out.print(memorization[i][j] + " ");
//				System.out.println("");
//			}

		}

		System.out.println(sb);
	}

	public static int dfs(int[][] arr, int i, int j, int[][] memorization) {

		if (memorization[i][j] != 0)
			return memorization[i][j];

		// 위로
		if (i - 1 >= 0 && arr[i - 1][j] == arr[i][j] + 1) {
			memorization[i - 1][j] = dfs(arr, i - 1, j, memorization);
			return memorization[i - 1][j] + 1;
		}
		// 아래로
		else if (i + 1 < arr.length && arr[i + 1][j] == arr[i][j] + 1) {
			memorization[i + 1][j] = dfs(arr, i + 1, j, memorization);
			return memorization[i + 1][j] + 1;
		}
		// 왼쪽
		else if (j - 1 >= 0 && arr[i][j - 1] == arr[i][j] + 1) {
			memorization[i][j - 1] = dfs(arr, i, j - 1, memorization);
			return memorization[i][j - 1] + 1;
		}
		// 오른쪽
		else if (j + 1 < arr[0].length && arr[i][j + 1] == arr[i][j] + 1) {
			memorization[i][j + 1] = dfs(arr, i, j + 1, memorization);
			return memorization[i][j + 1] + 1;
		}

		memorization[i][j] = 1;
		return memorization[i][j];

	}
}