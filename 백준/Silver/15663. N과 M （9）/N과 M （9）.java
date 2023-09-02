import java.io.*;
import java.util.*;

public class Main {

	public static HashMap<String, Boolean> answers = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		////////////////////////////////////////////////////////
		boolean[] visited = new boolean[N];
		permutation(arr, visited, M, 0, "");

		ArrayList<String> final_answers = new ArrayList<>();
		for (String key : answers.keySet()) {
			final_answers.add(key);
		}

		Collections.sort(final_answers, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String[] o1s = o1.split(" ");
				String[] o2s = o2.split(" ");

				for (int i = 0; i < o1s.length; i++) {
					if (Integer.parseInt(o1s[i]) > Integer.parseInt(o2s[i]))
						return 1;
					else if (Integer.parseInt(o1s[i]) < Integer.parseInt(o2s[i]))
						return -1;
				}

				return 0;
			}

		});

		for (String answer : final_answers)

			System.out.println(answer);

	}

	public static void permutation(int[] arr, boolean[] visited, int final_depth, int depth, String answer_candidate) {
		if (depth == final_depth) {
			answers.put(answer_candidate, true);
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				answer_candidate = answer_candidate + String.valueOf(arr[i]) + " ";
				permutation(arr, visited, final_depth, depth + 1, answer_candidate);
				visited[i] = false;
				answer_candidate = answer_candidate.substring(0, answer_candidate.length() - 1);

				int find_space = answer_candidate.lastIndexOf(" ");
				if (find_space == -1)
					answer_candidate = "";
				else
					answer_candidate = answer_candidate.substring(0, find_space + 1);
			}
		}

	}

}
