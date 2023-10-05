import java.io.*;
import java.util.*;

class Data {
	int vertex;
	int weight;

	Data(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[][] arr;

		/////////////////////////////////////////////////////

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		////////////////////////////////////////////////////

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/////////////////////////////////////////////////////

		// 섬을 먼저 넘버링하자
		int island_number = 0;
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					island_number++;
					dfs(arr, visited, i, j, island_number);
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}

		int[][] graph = new int[island_number + 1][island_number + 1];
		complete_graph(arr, graph);

//		for (int i = 0; i < graph.length; i++) {
//			for (int j = 0; j < graph[0].length; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}

		PriorityQueue<Data> pQ = new PriorityQueue<>(new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				if (o1.weight > o2.weight)
					return 1;
				else if (o1.weight == o2.weight)
					return 0;
				else
					return -1;
			}
		});

		boolean[] visited2 = new boolean[island_number + 1];

		for (int i = 1; i <= island_number; i++) {
			if (graph[1][i] != 0)
				pQ.add(new Data(i, graph[1][i]));
		}

		visited2[1] = true;

		int answer = 0;
		int vertex_count = 1;

		while (!pQ.isEmpty()) {
			Data tmp = pQ.remove();

			if (visited2[tmp.vertex])
				continue;

			visited2[tmp.vertex] = true;
			vertex_count++;
			answer += tmp.weight;

			if (vertex_count == island_number)
				break;

			for (int i = 1; i <= island_number; i++) {
				if (graph[tmp.vertex][i] != 0)
					pQ.add(new Data(i, graph[tmp.vertex][i]));
			}
		}
		if (vertex_count == island_number)
			System.out.println(answer);
		else
			System.out.println("-1");
	}

	public static void dfs(int[][] arr, boolean[][] visited, int i, int j, int island_number) {
		visited[i][j] = true;
		arr[i][j] = island_number;

		if (i - 1 >= 0 && !visited[i - 1][j] && arr[i - 1][j] == 1) {
			dfs(arr, visited, i - 1, j, island_number);
		}

		if (i + 1 < arr.length && !visited[i + 1][j] && arr[i + 1][j] == 1) {
			dfs(arr, visited, i + 1, j, island_number);
		}

		if (j - 1 >= 0 && !visited[i][j - 1] && arr[i][j - 1] == 1) {
			dfs(arr, visited, i, j - 1, island_number);
		}

		if (j + 1 < arr[0].length && !visited[i][j + 1] && arr[i][j + 1] == 1) {
			dfs(arr, visited, i, j + 1, island_number);
		}
	}

	public static void complete_graph(int[][] arr, int[][] graph) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0) {
					int my_island_number = arr[i][j];
					int y, x, count;

					// 왼쪽으로 가보자
					y = i;
					x = j - 1;
					count = 0;
					while (x >= 0) {
						if (arr[y][x] == my_island_number)
							break;
						else if (arr[y][x] == 0) {
							count++;
							x--;
						} else {
							if (count >= 2) {
								if (graph[my_island_number][arr[y][x]] == 0) {
									graph[my_island_number][arr[y][x]] = count;
									graph[arr[y][x]][my_island_number] = count;
								} else {
									graph[my_island_number][arr[y][x]] = Math.min(graph[my_island_number][arr[y][x]],
											count);
									graph[arr[y][x]][my_island_number] = Math.min(graph[arr[y][x]][my_island_number],
											count);
								}
							}

							break;
						}
					}

					// 오른쪽으로 가보자
					y = i;
					x = j + 1;
					count = 0;
					while (x < arr[0].length) {
						if (arr[y][x] == my_island_number)
							break;
						else if (arr[y][x] == 0) {
							count++;
							x++;
						} else {
							if (count >= 2) {
								if (graph[my_island_number][arr[y][x]] == 0) {
									graph[my_island_number][arr[y][x]] = count;
									graph[arr[y][x]][my_island_number] = count;
								} else {
									graph[my_island_number][arr[y][x]] = Math.min(graph[my_island_number][arr[y][x]],
											count);
									graph[arr[y][x]][my_island_number] = Math.min(graph[arr[y][x]][my_island_number],
											count);
								}
							}

							break;
						}
					}

					// 위쪽으로 가보자
					y = i - 1;
					x = j;
					count = 0;
					while (y >= 0) {
						if (arr[y][x] == my_island_number)
							break;
						else if (arr[y][x] == 0) {
							count++;
							y--;
						} else {
							if (count >= 2) {
								if (graph[my_island_number][arr[y][x]] == 0) {
									graph[my_island_number][arr[y][x]] = count;
									graph[arr[y][x]][my_island_number] = count;
								} else {
									graph[my_island_number][arr[y][x]] = Math.min(graph[my_island_number][arr[y][x]],
											count);
									graph[arr[y][x]][my_island_number] = Math.min(graph[arr[y][x]][my_island_number],
											count);
								}
							}

							break;
						}
					}

					// 아래쪽으로 가보자
					y = i + 1;
					x = j;
					count = 0;
					while (y < arr.length) {
						if (arr[y][x] == my_island_number)
							break;
						else if (arr[y][x] == 0) {
							count++;
							y++;
						} else {
							if (count >= 2) {
								if (graph[my_island_number][arr[y][x]] == 0) {
									graph[my_island_number][arr[y][x]] = count;
									graph[arr[y][x]][my_island_number] = count;
								} else {
									graph[my_island_number][arr[y][x]] = Math.min(graph[my_island_number][arr[y][x]],
											count);
									graph[arr[y][x]][my_island_number] = Math.min(graph[arr[y][x]][my_island_number],
											count);
								}
							}

							break;
						}
					}
				}
			}
		}
	}
}
