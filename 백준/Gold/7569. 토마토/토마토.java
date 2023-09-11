import java.io.*;
import java.util.*;

class Data {
	int i, j, k;
	int depth;

	Data(int i, int j, int k, int depth) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.depth = depth;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int M, N, H;
		int[][][] arr;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < N; j++) {
//				for (int k = 0; k < M; k++) {
//					System.out.print(arr[i][j][k]+" ");
//				}
//				System.out.println();
//			}
//		}

		/////////////////////////////////////////////////////////////

		Queue<Data> queue = new LinkedList<>();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 1) {
						queue.add(new Data(i, j, k, 0));
					}
				}
			}
		}

		int answer = 0;

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();
			int i = tmp.i;
			int j = tmp.j;
			int k = tmp.k;
			int depth = tmp.depth;

			answer = Math.max(answer, depth);

			// 위
			if (i - 1 >= 0 && arr[i - 1][j][k] == 0) {
				arr[i - 1][j][k] = 1;
				queue.add(new Data(i - 1, j, k, depth + 1));
			}

			// 아래
			if (i + 1 < arr.length && arr[i + 1][j][k] == 0) {
				arr[i + 1][j][k] = 1;
				queue.add(new Data(i + 1, j, k, depth + 1));
			}

			// 앞
			if (j - 1 >= 0 && arr[i][j - 1][k] == 0) {
				arr[i][j - 1][k] = 1;
				queue.add(new Data(i, j - 1, k, depth + 1));
			}

			// 뒤
			if (j + 1 < arr[0].length && arr[i][j + 1][k] == 0) {
				arr[i][j + 1][k] = 1;
				queue.add(new Data(i, j + 1, k, depth + 1));
			}

			// 왼쪽
			if (k - 1 >= 0 && arr[i][j][k - 1] == 0) {
				arr[i][j][k - 1] = 1;
				queue.add(new Data(i, j, k - 1, depth + 1));
			}

			// 오른쪽
			if (k + 1 < arr[0][0].length && arr[i][j][k + 1] == 0) {
				arr[i][j][k + 1] = 1;
				queue.add(new Data(i, j, k + 1, depth + 1));
			}
		}

		boolean can = true;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 0) {
						can = false;
					}
				}
			}
		}
		
		if(can)
			System.out.println(answer);
		else
			System.out.println("-1");

	}
}
