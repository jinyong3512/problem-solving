import java.io.*;
import java.util.*;

public class Main {
	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		/////////////////////////////////////////////////////////////

		answer = 0;

		// 순열
		boolean[] visited = new boolean[N];
		recursion(arr, 0, 0, visited);

		System.out.println(answer);

	}

	public static void recursion(int[] arr, int depth, int W, boolean[] visited) {
		if (depth == arr.length - 2) {
			answer = Math.max(answer, W);
			return;
		}

		for (int i = 1; i < arr.length - 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int left_value = -1;
				int right_value = -1;

				for (int j = i - 1; j >= 0; j--) {
					if (!visited[j]) {
						left_value = arr[j];
						break;
					}
				}

				for (int j = i + 1; j < arr.length; j++) {
					if (!visited[j]) {
						right_value = arr[j];
						break;
					}
				}

				recursion(arr, depth + 1, W + left_value * right_value, visited);
				visited[i] = false;
			}
		}
	}
}