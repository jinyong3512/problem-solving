import java.io.*;
import java.util.*;

class Data {
	int y;
	int x;
	int depth;
	boolean chance;

	Data(int y, int x, int depth, boolean chance) {
		this.y = y;
		this.x = x;
		this.depth = depth;
		this.chance = chance;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		///////////////////////////////////////////////////

		Queue<Data> queue = new LinkedList<>();
		// 찬스 안쓴애
		boolean[][] visited = new boolean[N][M];
		// 찬스 쓴애
		boolean[][] visited2 = new boolean[N][M];

		queue.add(new Data(0, 0, 1, true));

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			if (tmp.y == N - 1 && tmp.x == M - 1) {
				sb.append(tmp.depth);
				break;
			}

			// 찬스가 있네
			if (tmp.chance) {
				// 찬스 쓰기
				// 위로
				if (tmp.y - 1 >= 0 && !visited2[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] == 1) {
					visited2[tmp.y - 1][tmp.x] = true;
					queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1, !tmp.chance));
				}

				// 아래로
				if (tmp.y + 1 < N && !visited2[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] == 1) {
					visited2[tmp.y + 1][tmp.x] = true;
					queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1, !tmp.chance));
				}

				// 왼쪽
				if (tmp.x - 1 >= 0 && !visited2[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] == 1) {
					visited2[tmp.y][tmp.x - 1] = true;
					queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1, !tmp.chance));
				}

				// 오른쪽
				if (tmp.x + 1 < M && !visited2[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] == 1) {
					visited2[tmp.y][tmp.x + 1] = true;
					queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1, !tmp.chance));
				}
				
				// 찬스 있는데 안쓸래
				// 위로
				if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] == 0) {
					visited[tmp.y - 1][tmp.x] = true;
					queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1, tmp.chance));
				}

				// 아래로
				if (tmp.y + 1 < N && !visited[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] == 0) {
					visited[tmp.y + 1][tmp.x] = true;
					queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1, tmp.chance));
				}

				// 왼쪽
				if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] == 0) {
					visited[tmp.y][tmp.x - 1] = true;
					queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1, tmp.chance));
				}

				// 오른쪽
				if (tmp.x + 1 < M && !visited[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] == 0) {
					visited[tmp.y][tmp.x + 1] = true;
					queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1, tmp.chance));
				}
			} 
			// 찬스 없음
			else {

				// 위로
				if (tmp.y - 1 >= 0 && !visited2[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] == 0) {
					visited2[tmp.y - 1][tmp.x] = true;
					queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1, tmp.chance));
				}

				// 아래로
				if (tmp.y + 1 < N && !visited2[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] == 0) {
					visited2[tmp.y + 1][tmp.x] = true;
					queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1, tmp.chance));
				}

				// 왼쪽
				if (tmp.x - 1 >= 0 && !visited2[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] == 0) {
					visited2[tmp.y][tmp.x - 1] = true;
					queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1, tmp.chance));
				}

				// 오른쪽
				if (tmp.x + 1 < M && !visited2[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] == 0) {
					visited2[tmp.y][tmp.x + 1] = true;
					queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1, tmp.chance));
				}
			}

		}

		if (sb.length() == 0)
			sb.append("-1");
		System.out.println(sb);
	}
}