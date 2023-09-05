import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, B;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/////////////////////////////////////////////////

		int answer_time = Integer.MAX_VALUE;
		int answer_height = -1;

		for (int height = 0; height <= 256; height++) {
			int time = 0;
			int B_tmp = B;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] > height) {
						time = time + 2 * (arr[i][j] - height);
						B_tmp = B_tmp + (arr[i][j] - height);
					} else if (arr[i][j] == height) {

					} else {
						time = time + (height - arr[i][j]);
						B_tmp = B_tmp - (height - arr[i][j]);
					}
				}
			}

			if (B_tmp >= 0 && answer_time >= time) {
				answer_time = time;
				answer_height = height;
			}

		}

		System.out.println(answer_time + " " + answer_height);
	}
}