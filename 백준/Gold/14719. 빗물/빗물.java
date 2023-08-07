import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int H, W;
		boolean[][] arr;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new boolean[H][W];

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < W; j++) {
			int tmp_height = Integer.parseInt(st.nextToken());
			for (int i = 0; i < tmp_height; i++) {
				arr[i][j] = true;
			}
		}

		///////////////////////////////////////////////

		int answer = 0;

		for (int i = 0; i < H; i++) {
			// 맨 왼쪽 벽
			int left = -1;
			// 맨 오른쪽 벽
			int right = -1;

			for (int j = 0; j < W; j++) {
				if (arr[i][j]) {
					left = j;
					break;
				}
			}

			for (int j = W - 1; j >= 0; j--) {
				if (arr[i][j]) {
					right = j;
					break;
				}
			}

			if (left != -1 && right != -1) {
				for (int j = left + 1; j < right; j++) {
					if (!arr[i][j])
						answer++;
				}
			}
		}
		System.out.println(answer);

	}

}
