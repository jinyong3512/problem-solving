import java.io.*;
import java.util.*;

public class Main {
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[][] arr;
		N = Integer.parseInt(br.readLine());

		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		/////////////////////////////////////////////////

		answer = Integer.MAX_VALUE;

		// 중복 x 조합
		boolean[] visited = new boolean[N];
		for (int r = 1; r <= N; r++)
			recursion(N, arr, visited, 1, 0, 0, 0, r);
		
		sb.append(answer);
		System.out.println(answer);

	}

	public static void recursion(int N, int[][] arr, boolean[] visited, int S, int B, int depth, int start_index,
			int r) {
		if (depth == r) {
			answer = Math.min(answer, Math.abs(S - B));
		}

		for (int i = start_index; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursion(N, arr, visited, S * arr[i][0], B + arr[i][1], depth + 1, i, r);
				visited[i] = false;
			}
		}
	}
}