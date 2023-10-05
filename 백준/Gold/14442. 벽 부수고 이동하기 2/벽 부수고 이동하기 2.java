import java.io.*;
import java.util.*;

class Data {
	int y, x, k, depth;

	Data(int y, int x, int k, int depth) {
		this.y = y;
		this.x = x;
		this.k = k;
		this.depth = depth;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, K;
		char[][] arr;

		/////////////////////////////
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j);
			}
		}

		////////////////////////////////////////

		int[][] visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = K + 1;
			}
		}

		Queue<Data> queue = new LinkedList<>();
		queue.add(new Data(0, 0, 0, 1));

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			if (tmp.y == N - 1 && tmp.x == M - 1) {
				sb.append(tmp.depth);
				break;
			}
			// 벽 부시기 기회 있어
			if (tmp.k < K) {

				// 위로
				if (tmp.y - 1 >= 0 && visited[tmp.y - 1][tmp.x] > tmp.k + 1 && arr[tmp.y - 1][tmp.x] == '1') {
					visited[tmp.y - 1][tmp.x] = tmp.k + 1;
					queue.add(new Data(tmp.y - 1, tmp.x, tmp.k + 1, tmp.depth + 1));
				}
				// 아래로
				if (tmp.y + 1 < arr.length && visited[tmp.y + 1][tmp.x] > tmp.k + 1 && arr[tmp.y + 1][tmp.x] == '1') {
					visited[tmp.y + 1][tmp.x] = tmp.k + 1;
					queue.add(new Data(tmp.y + 1, tmp.x, tmp.k + 1, tmp.depth + 1));
				}
				// 왼쪽로
				if (tmp.x - 1 >= 0 && visited[tmp.y][tmp.x - 1] > tmp.k + 1 && arr[tmp.y][tmp.x - 1] == '1') {
					visited[tmp.y][tmp.x - 1] = tmp.k + 1;
					queue.add(new Data(tmp.y, tmp.x - 1, tmp.k + 1, tmp.depth + 1));
				}
				// 오른로
				if (tmp.x + 1 < arr[0].length && visited[tmp.y][tmp.x + 1] > tmp.k + 1 && arr[tmp.y][tmp.x + 1] == '1') {
					visited[tmp.y][tmp.x + 1] = tmp.k + 1;
					queue.add(new Data(tmp.y, tmp.x + 1, tmp.k + 1, tmp.depth + 1));
				}

			}
			// 위로
			if (tmp.y - 1 >= 0 && visited[tmp.y - 1][tmp.x] > tmp.k && arr[tmp.y - 1][tmp.x] == '0') {
				visited[tmp.y - 1][tmp.x] = tmp.k;
				queue.add(new Data(tmp.y - 1, tmp.x, tmp.k, tmp.depth + 1));
			}
			// 아래로
			if (tmp.y + 1 < arr.length && visited[tmp.y + 1][tmp.x] > tmp.k && arr[tmp.y + 1][tmp.x] == '0') {
				visited[tmp.y + 1][tmp.x] = tmp.k;
				queue.add(new Data(tmp.y + 1, tmp.x, tmp.k, tmp.depth + 1));
			}
			// 왼쪽로
			if (tmp.x - 1 >= 0 && visited[tmp.y][tmp.x - 1] > tmp.k && arr[tmp.y][tmp.x - 1] == '0') {
				visited[tmp.y][tmp.x - 1] = tmp.k;
				queue.add(new Data(tmp.y, tmp.x - 1, tmp.k, tmp.depth + 1));
			}
			// 오른로
			if (tmp.x + 1 < arr[0].length && visited[tmp.y][tmp.x + 1] > tmp.k && arr[tmp.y][tmp.x + 1] == '0') {
				visited[tmp.y][tmp.x + 1] = tmp.k;
				queue.add(new Data(tmp.y, tmp.x + 1, tmp.k, tmp.depth + 1));
			}

		}

		if (sb.length() == 0)
			sb.append("-1");

		System.out.println(sb);

	}
}
