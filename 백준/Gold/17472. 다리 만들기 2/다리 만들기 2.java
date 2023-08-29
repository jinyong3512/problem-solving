import java.io.*;
import java.util.*;

class Edge {
	int end;
	int weight;

	Edge(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[][] map;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//////////////////////////////////////////////////////////

		// 섬을 넘버링 하기
		int island_number = 0;
		boolean[][] visited = new boolean[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					island_number++;
					dfs_numbering_island(map, visited, island_number, i, j);
				}
			}
		}

		// 그래프 만들어!
		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= island_number; i++)
			graph.add(new ArrayList<Edge>());

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != 0) {

					// 위로 다리 무조건 그어
					int length = -1;
					for (int y = i - 1; y >= 0; y--) {
						length++;
						// 바다다
						if (map[y][j] == 0)
							continue;
						// 내 섬이다 ㅋ?
						if (map[y][j] == map[i][j])
							break;
						// 남의 섬이다!
						else {
							if (length == 1)
								break;
							else {
								graph.get(map[i][j]).add(new Edge(map[y][j], length));
								break;
							}
						}
					}

					// 아래로 다리 무조건 그어
					length = -1;
					for (int y = i + 1; y < map.length; y++) {
						length++;
						// 바다다
						if (map[y][j] == 0)
							continue;
						// 내 섬이다 ㅋ?
						if (map[y][j] == map[i][j])
							break;
						// 남의 섬이다!
						else {
							if (length == 1)
								break;
							else {
								graph.get(map[i][j]).add(new Edge(map[y][j], length));
								break;
							}
						}
					}

					// 왼쪽으로 다리 무조건 그어
					length = -1;
					for (int x = j - 1; x >= 0; x--) {
						length++;
						// 바다다
						if (map[i][x] == 0)
							continue;
						// 내 섬이다 ㅋ?
						if (map[i][j] == map[i][x])
							break;
						// 남의 섬이다!
						else {
							if (length == 1)
								break;
							else {
								graph.get(map[i][j]).add(new Edge(map[i][x], length));
								break;
							}
						}
					}

					// 오른쪽으로 다리 무조건 그어
					length = -1;
					for (int x = j + 1; x < map[0].length; x++) {
						length++;
						// 바다다
						if (map[i][x] == 0)
							continue;
						// 내 섬이다 ㅋ?
						if (map[i][j] == map[i][x])
							break;
						// 남의 섬이다!
						else {
							if (length == 1)
								break;
							else {
								graph.get(map[i][j]).add(new Edge(map[i][x], length));
								break;
							}
						}
					}
				}
			}
		}

		PriorityQueue<Edge> pQ = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.weight > o2.weight)
					return 1;
				else if (o1.weight == o2.weight)
					return 0;
				else
					return -1;
			}
		});
		boolean[] visited2 = new boolean[island_number + 1];

		visited2[1] = true;
		for (Edge edge : graph.get(1))
			pQ.add(edge);

		int answer = 0;
		while (!pQ.isEmpty()) {
			Edge tmp = pQ.remove();
			if (!visited2[tmp.end]) {
				visited2[tmp.end] = true;
				answer += tmp.weight;
				for (Edge edge : graph.get(tmp.end))
					pQ.add(edge);
			}
		}

		boolean find = true;
		for (int i = 1; i < visited2.length; i++) {
			if (!visited2[i])
				find = false;
		}

		if (find) {
			System.out.println(answer);
		} else {
			System.out.println("-1");
		}

	}

	public static void dfs_numbering_island(int[][] map, boolean[][] visited, int island_number, int y, int x) {
		visited[y][x] = true;
		map[y][x] = island_number;

		// 위
		if (y - 1 >= 0 && !visited[y - 1][x] && map[y - 1][x] != 0) {
			dfs_numbering_island(map, visited, island_number, y - 1, x);
		}

		// 아래
		if (y + 1 < map.length && !visited[y + 1][x] && map[y + 1][x] != 0) {
			dfs_numbering_island(map, visited, island_number, y + 1, x);
		}

		// 왼쪽
		if (x - 1 >= 0 && !visited[y][x - 1] && map[y][x - 1] != 0) {
			dfs_numbering_island(map, visited, island_number, y, x - 1);
		}

		// 오른쪽
		if (x + 1 < map[0].length && !visited[y][x + 1] && map[y][x + 1] != 0) {
			dfs_numbering_island(map, visited, island_number, y, x + 1);
		}

	}

	public static void print_map(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println(" ");
		}
	}

}