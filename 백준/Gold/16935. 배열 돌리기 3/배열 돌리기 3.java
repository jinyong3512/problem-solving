import java.io.*;
import java.util.*;

class Main {

	public static int N, M, R;
	public static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		///////////////////////////////////////////////////////////////////////

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			String number = st.nextToken();

			if (number.contentEquals("1"))
				func1();
			else if (number.contentEquals("2"))
				func2();
			else if (number.contentEquals("3"))
				func3();
			else if (number.contentEquals("4"))
				func4();
			else if (number.contentEquals("5"))
				func5();
			else if (number.contentEquals("6"))
				func6();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void func1() {
		int[][] new_arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[N - 1 - i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func2() {
		int[][] new_arr = new int[N][M];

		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				new_arr[i][M - 1 - j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func3() {
		int[][] new_arr = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[j][N - 1 - i] = arr[i][j];
			}
		}

		arr = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}

		int tmp = M;
		M = N;
		N = tmp;
	}

	public static void func4() {
		int[][] new_arr = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[M - 1 - j][i] = arr[i][j];
			}
		}

		arr = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}

		int tmp = M;
		M = N;
		N = tmp;
	}

	public static void func5() {
		int[][] new_arr = new int[N][M];

		// 1 -> 2
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i][j + M / 2] = arr[i][j];
			}
		}

		// 2 -> 3
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i + N / 2][j] = arr[i][j];
			}
		}

		// 3 -> 4
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i][j - M / 2] = arr[i][j];
			}
		}

		// 4 -> 1
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i - N / 2][j] = arr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func6() {
		int[][] new_arr = new int[N][M];

		// 1 -> 4
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i + N / 2][j] = arr[i][j];
			}
		}

		// 2 -> 1
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i][j - M / 2] = arr[i][j];
			}
		}

		// 3 -> 2
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i - N / 2][j] = arr[i][j];
			}
		}

		// 4 -> 3
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i][j + M / 2] = arr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}
}