import java.io.*;
import java.util.*;

class Point2 {
	int r, c, value;
	int flag;
	// flag가 0 -> 기회안 쓴애
	// flag가 1 -> 기회 쓴애
	// boolean flag 에서 바꾼 이유 visited[0][1] 인덱스 그대로 쓰려고
	// boolean 쓰면 if(flag) visited[1]에 저장 이런식으로 한번 더 if문을 써야 할 것 같아서

	Point2(int r, int c, int value, int flag) {
		this.r = r;
		this.c = c;
		this.value = value;
		this.flag = flag;
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
		boolean[][][] visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = (int) (str.charAt(j) - '0');
			}
		}

		//////////////////////////////////////////////////////////////

		Queue<Point2> que = new ArrayDeque<>();

		que.offer(new Point2(0, 0, 1, 0)); // 시작점과 한칸째라고 쓰고 맨 처음에는 부시기 기회 안 쓴애~
		visited[0][0][0] = true; // visited[0]은 부시기 기회 안쓴애로 쓰기로 약속 visited[1]은 부시기 쓴애로 약속

		while (!que.isEmpty()) {
			Point2 curr = que.poll();

			// 도착 지점 종료
			if (curr.r == N-1 && curr.c == M-1) {
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
					// 나 찬스 있어!
					if (curr.flag == 0) {
						// 찬스 쓸꺼야!
						if (!visited[row][col][1] && map[row][col] == 1) {
							visited[row][col][1] = true;
							que.offer(new Point2(row, col, curr.value + 1, 1));
						}
						// 찬스 안써
						if (!visited[row][col][0] && map[row][col] == 0) {
							visited[row][col][0] = true;
							que.offer(new Point2(row, col, curr.value + 1, 0));
						}
					}

					// 나 찬스 없음!
					else {
						// 찬스 쓸꺼야!
						// 안돼

						// 찬스 안써
						if (!visited[row][col][1] && map[row][col] == 0) {
							visited[row][col][1] = true;
							que.offer(new Point2(row, col, curr.value + 1, 1));
						}
					}

//					// 찬스 쓴애야 || 부실벽이 없음
//					if (curr.flag || map[row][col] == 0) {
//						visited[row][col][0] = true;
//						que.offer(new Point2(row, col, curr.value + 1, curr.flag));
//					} else { // 벽 부실 기회 있음
//						if (map[row][col] == 1) { // 부신다
//							visited[row][col][0] = true;
//							visited[row][col][1] = true;
//							que.offer(new Point2(row, col, curr.value + 1, true));
//						} else { // 안 부신다
//							que.offer(new Point2(row, col, curr.value + 1, curr.flag));
//						}
//					}
				}

			}

		}
		bw.write("-1");
		bw.flush();
		bw.close();

	}
}