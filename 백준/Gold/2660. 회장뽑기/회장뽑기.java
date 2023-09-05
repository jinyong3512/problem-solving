import java.io.*;
import java.util.*;

class Data {
	int vertex;
	int depth;

	Data(int vertex, int depth) {
		this.vertex = vertex;
		this.depth = depth;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		boolean[][] graph;

		N = Integer.parseInt(br.readLine());
		graph = new boolean[N][N];
		while (true) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;

			if (v1 == -2 && v2 == -2)
				break;

			graph[v1][v2] = true;
			graph[v2][v1] = true;
		}

		////////////////////////////////////////

		int[] scores = new int[N];

		// 모든 정점에 대해 bfs
		for (int i = 0; i < N; i++) {
			Queue<Data> queue = new LinkedList<>();
			boolean[] visited = new boolean[N];

			queue.add(new Data(i, 0));
			visited[i] = true;

			while (!queue.isEmpty()) {
				Data tmp = queue.remove();

				scores[i] = Math.max(scores[i], tmp.depth);

				for (int j = 0; j < N; j++) {
					if (graph[tmp.vertex][j] && !visited[j]) {
						visited[j] = true;
						queue.add(new Data(j, tmp.depth + 1));
					}
				}
			}
		}
		
		
		int answer_score= Integer.MAX_VALUE;
		for(int i =0 ; i < N ; i++) {
			answer_score = Math.min(answer_score,scores[i]);
		}
		
		int answer_number = 0;
		for(int i =0 ; i < N ; i++) {
			if(answer_score == scores[i])
				answer_number++;
		}
		
		System.out.println(answer_score+" "+answer_number);
		for(int i =0 ; i < N ; i++) {
			if(answer_score == scores[i])
			System.out.print(i+1+" ");
		}
		System.out.println();
		
		

	}
}