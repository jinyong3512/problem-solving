import java.io.*;
import java.util.*;

class Point2 {
	int r, c, value;
	int use_chance_count;

	Point2(int r, int c, int value, int use_chance_count) {
		this.r = r;
		this.c = c;
		this.value = value;
		this.use_chance_count = use_chance_count;
	}
}

public class Main {

	static int[] dr = new int[] { 0, 1, 0, -1 };
	static int[] dc = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = (int) (str.charAt(j) - '0');
			}
		}

		//////////////////////////////////////////////////////////////

		Queue<Point2> que = new ArrayDeque<>();
		int[][] point_use_chance = new int[N][M];
		// 찬스 1번 쓴애가 해당 지점에 가면 큐에 넣을꺼임 근데 0번 쓴애가 해당 지점에가면 큐에 또 넣을꺼임 그러니 2로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				point_use_chance[i][j] = 2;
			}
		}

		que.offer(new Point2(0, 0, 1, 0)); // 시작점과 한칸째라고 쓰고 맨 처음에는 부시기 기회 안 쓴애~
		point_use_chance[0][0] = 0; // 0 -> 찬스 안쓴애가 방문 했었습니다~

		while (!que.isEmpty()) {
			Point2 curr = que.poll();

			// 도착 지점 종료
			if (curr.r == N - 1 && curr.c == M - 1) {
				sb.append(curr.value);
				bw.write(sb.toString());
				bw.flush();
				bw.close();
				return;
			}

			for (int d = 0; d < 4; d++) {

				int row = curr.r + dr[d];
				int col = curr.c + dc[d];

				// 칸 안에 있어?
				if (-1 < row && row < N && -1 < col && col < M) {
					// 찬스 있어
					if (curr.use_chance_count == 0) {
						// 찬스 쓸래
						if (point_use_chance[row][col] > curr.use_chance_count + 1 && map[row][col] == 1) {
							point_use_chance[row][col] = curr.use_chance_count + 1;
							que.add(new Point2(row, col, curr.value + 1, curr.use_chance_count + 1));
						}
					}
					// 찬스 있던 없던 안쓴다
					if (point_use_chance[row][col] > curr.use_chance_count && map[row][col] == 0) {
						point_use_chance[row][col] = curr.use_chance_count;
						que.add(new Point2(row, col, curr.value + 1, curr.use_chance_count));
					}

				}

			}

		}
		bw.write("-1");
		bw.flush();
		bw.close();

	}
}