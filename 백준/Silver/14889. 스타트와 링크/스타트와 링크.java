import java.io.*;
import java.util.*;

public class Main {

	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		////////////////////////////////////////////////////////////

		// 조합 n C n/2
		boolean[] visited = new boolean[arr.length];
		recursion(arr, 0, visited, -1);
		
		System.out.println(answer);
	}

	public static void recursion(int[][] arr, int depth, boolean[] visited, int last_pick) {
		if (depth == arr.length / 2) {
			ArrayList<Integer> visited_team = new ArrayList<>();
			ArrayList<Integer> not_visited_team = new ArrayList<>();

			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					visited_team.add(i);
				} else {
					not_visited_team.add(i);
				}
			}

			boolean[] visited2 = new boolean[visited_team.size()];
			boolean[] visited3 = new boolean[not_visited_team.size()];
			answer = Math.min(answer, Math.abs(recursion2(arr, visited_team, visited2, 0, -1)
					- recursion2(arr, not_visited_team, visited2, 0, -1)));

			return;
		}

		for (int i = last_pick + 1; i < arr.length; i++) {
			visited[i] = true;
			recursion(arr, depth + 1, visited, i);
			visited[i] = false;
		}
	}

	public static int recursion2(int[][] arr, ArrayList<Integer> team, boolean[] visited, int depth, int last_pick) {
		if (depth == 2) {
			int player1 = -1;
			int player2 = -1;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					if (player1 == -1)
						player1 = team.get(i);
					else
						player2 = team.get(i);
				}
			}

			return arr[player1][player2] + arr[player2][player1];
		}
		int sum = 0;
		for (int i = last_pick + 1; i < team.size(); i++) {
			visited[i] = true;
			sum += recursion2(arr, team, visited, depth + 1, i);
			visited[i] = false;
		}

		return sum;
	}
}