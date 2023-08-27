import java.io.*;
import java.util.*;

public class Main {

	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		boolean[][] arr = new boolean[10][10];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				if (Integer.parseInt(st.nextToken()) == 0)
					arr[i][j] = false;
				else
					arr[i][j] = true;
			}
		}

		/////////////////////////////////////////////////

		int[] papers = new int[] { 5, 5, 5, 5, 5 };
		recursion(arr, papers, 0);

		if (answer == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(answer);

	}

	public static void recursion(boolean[][] arr, int[] papers, int index) {

		if (index == 100) {

			int count = 0;
			for (int i = 0; i < papers.length; i++) {
				count += (5 - papers[i]);
			}

			answer = Math.min(answer, count);

			return;
		}

		if (arr[index / 10][index % 10]) {

			for (int paper_size = 5; paper_size >= 1; paper_size--) {

				if (papers[paper_size - 1] == 0)
					continue;

				boolean can = true;
				for (int i = index / 10; i < index / 10 + paper_size; i++) {

					if (i >= 10) {
						can = false;
						break;
					}

					for (int j = index % 10; j < index % 10 + paper_size; j++) {
						if (j >= 10) {
							can = false;
							break;
						}

						if (!arr[i][j]) {
							can = false;
						}
					}
				}
				if (can) {
					for (int i = index / 10; i < index / 10 + paper_size; i++) {
						for (int j = index % 10; j < index % 10 + paper_size; j++) {
							arr[i][j] = false;
						}
					}
					papers[paper_size - 1]--;
					recursion(arr, papers, index + 1);
					for (int i = index / 10; i < index / 10 + paper_size; i++) {
						for (int j = index % 10; j < index % 10 + paper_size; j++) {
							arr[i][j] = true;
						}
					}
					papers[paper_size - 1]++;
				}
			}

		} else {
			recursion(arr, papers, index + 1);
		}

	}

}
