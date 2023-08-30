import java.io.*;
import java.util.*;

public class Main {

	public static int answer = Integer.MIN_VALUE;

	public static int recursion_count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		///////////////////////////////////////////////////

		// 순열
		boolean[] visited = new boolean[9];
		int[] batter = new int[9];
		permutation(arr, visited, batter, 0);

		System.out.println(answer);
//		System.out.println(recursion_count);

	}

	public static void permutation(int[][] arr, boolean[] visited, int[] batter, int depth) {

		recursion_count++;

		if (depth == 3) {
			visited[0] = true;
			batter[depth] = 0;
			permutation(arr, visited, batter, depth + 1);
			visited[0] = false;
		}

		if (depth == 9) {
			answer = Math.max(answer, simulation2(arr, batter));
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i] && i != 0) {
				visited[i] = true;
				batter[depth] = i;
				permutation(arr, visited, batter, depth + 1);
				visited[i] = false;
			}
		}
	}

	public static int simulation(int[][] arr, int[] batter) {
		int arr_i_index = 0;
		int batter_index = 0;
		int out_number = 0;
		int answer_candidate = 0;
		Queue<Boolean> queue = new LinkedList<>();
		queue.add(false);
		queue.add(false);
		queue.add(false);

		while (arr_i_index < arr.length) {
			if (arr[arr_i_index][batter[batter_index]] == 0) {
				out_number++;
				if (out_number == 3) {
					out_number = 0;
					arr_i_index++;
					queue = new LinkedList<>();
					queue.add(false);
					queue.add(false);
					queue.add(false);
				}
			} else if (arr[arr_i_index][batter[batter_index]] == 1) {
				queue.add(true);
				if (queue.remove())
					answer_candidate++;
			} else if (arr[arr_i_index][batter[batter_index]] == 2) {
				queue.add(true);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;
			} else if (arr[arr_i_index][batter[batter_index]] == 3) {
				queue.add(true);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;
			} else if (arr[arr_i_index][batter[batter_index]] == 4) {
				queue.add(true);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;

				queue.add(false);
				if (queue.remove())
					answer_candidate++;
			}

			batter_index = (batter_index + 1) % 9;
		}

		return answer_candidate;
	}

	public static int simulation2(int[][] arr, int[] batter) {
		int arr_i_index = 0;
		int batter_index = 0;
		int out_number = 0;
		int answer_candidate = 0;

		boolean base1 = false;
		boolean base2 = false;
		boolean base3 = false;

		while (arr_i_index < arr.length) {
			if (arr[arr_i_index][batter[batter_index]] == 0) {
				out_number++;
				if (out_number == 3) {
					out_number = 0;
					arr_i_index++;
					base1 = false;
					base2 = false;
					base3 = false;
				}
			} else if (arr[arr_i_index][batter[batter_index]] == 1) {
				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = true;
			} else if (arr[arr_i_index][batter[batter_index]] == 2) {
				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = true;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;
			} else if (arr[arr_i_index][batter[batter_index]] == 3) {
				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = true;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;
			} else if (arr[arr_i_index][batter[batter_index]] == 4) {
				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = true;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;

				if (base3)
					answer_candidate++;
				base3 = base2;
				base2 = base1;
				base1 = false;
			}

			batter_index = (batter_index + 1) % 9;
		}

		return answer_candidate;
	}
}