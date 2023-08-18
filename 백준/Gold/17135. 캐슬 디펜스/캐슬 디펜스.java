import java.io.*;
import java.util.*;

//

class Point {
	int y, x;
	int depth;

	Point(int y, int x, int depth) {
		this.y = y;
		this.x = x;
		this.depth = depth;
	}
}

class Main {
	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int N, M, D;
		boolean[][] enemy;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemy = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					enemy[i][j] = true;
			}
		}

		//////////////////////////////////////////////

		// 조합 이다 MC3 0 ~ M-1 중에 3개 뽑기
		boolean[] visited = new boolean[M];

		recursion(M, 3, visited, 0, -1, enemy, D);

		System.out.println(answer);
	}

	public static void recursion(int M, int r, boolean[] visited, int depth, int last_pick, boolean[][] enemy, int D) {
		if (depth == r) {

			boolean[][] new_enemy = new boolean[enemy.length][enemy[0].length];
			for (int i = 0; i < enemy.length; i++) {
				for (int j = 0; j < enemy[0].length; j++) {
					new_enemy[i][j] = enemy[i][j];
				}
			}
			simulation(new_enemy, D, visited);
			return;
		}

		for (int i = last_pick + 1; i < M; i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursion(M, r, visited, depth + 1, i, enemy, D);
				visited[i] = false;
			}
		}

	}

	public static void simulation(boolean[][] enemy, int D, boolean[] visited) {
		int kill_count = 0;
		while (true) {

			boolean empty = true;
			for (int i = 0; i < enemy.length; i++) {
				for (int j = 0; j < enemy[0].length; j++) {
					if (enemy[i][j])
						empty = false;
				}
			}

			if (empty) {
				answer = Math.max(answer, kill_count);
				break;
			}

			ArrayList<Point> arrayList = new ArrayList<>();

			for (int j = 0; j < visited.length; j++) {
				// 궁수가 있으면
				if (visited[j]) {
					PriorityQueue<Point> pQ = new PriorityQueue<Point>(new Comparator<Point>() {

						@Override
						public int compare(Point o1, Point o2) {
							if (o1.depth > o2.depth)
								return 1;
							else if (o1.depth == o2.depth) {
								if(o1.x > o2.x)
									return 1;
								else if (o1.x==o2.x) {
									return 0;
								}
								else
									return -1;
							}
							else
								return -1;
						}
					});

					boolean[][] visited2 = new boolean[enemy.length][enemy[0].length];
					visited2[enemy.length - 1][j] = true;

					pQ.add(new Point(enemy.length - 1, j, 1));

					while (!pQ.isEmpty()) {
						Point tmp = pQ.remove();

						if (tmp.depth > D)
							break;

						if (enemy[tmp.y][tmp.x]) {
							arrayList.add(tmp);
							break;
						}

						// 왼쪽
						if (tmp.x - 1 >= 0 && !visited2[tmp.y][tmp.x - 1]) {
							visited2[tmp.y][tmp.x - 1] = true;
							pQ.add(new Point(tmp.y, tmp.x - 1, tmp.depth + 1));
						}

						// 위
						if (tmp.y - 1 >= 0 && !visited2[tmp.y - 1][tmp.x]) {
							visited2[tmp.y - 1][tmp.x] = true;
							pQ.add(new Point(tmp.y - 1, tmp.x, tmp.depth + 1));
						}

						// 오른쪽
						if (tmp.x + 1 < enemy[0].length && !visited2[tmp.y][tmp.x + 1]) {
							visited2[tmp.y][tmp.x + 1] = true;
							pQ.add(new Point(tmp.y, tmp.x + 1, tmp.depth + 1));
						}
					}

				}
			}

			for (Point point : arrayList) {
				if (enemy[point.y][point.x]) {
					enemy[point.y][point.x] = false;
					kill_count++;
				}
			}

			for (int y = enemy.length - 1; y > 0; y--) {
				for (int x = 0; x < enemy[0].length; x++) {
					enemy[y][x] = enemy[y - 1][x];
				}
			}

			for (int x = 0; x < enemy[0].length; x++)
				enemy[0][x] = false;

		}
	}
}