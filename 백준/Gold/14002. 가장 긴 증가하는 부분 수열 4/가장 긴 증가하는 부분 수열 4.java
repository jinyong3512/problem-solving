import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[] arr;

		/////////////////////////////////////////////

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//////////////////////////////////////////////

		ArrayList<ArrayList<Integer>> answers = new ArrayList<>();
		for (int i = 0; i < arr.length; i++)
			answers.add(new ArrayList<Integer>());

		// dp i는 i번째 숫자를 무조건 선택 했을 때 만들 수 있는 가장 긴 증가하는 부분 수열
		int[] dp = new int[arr.length];

		answers.get(0).add(arr[0]);
		dp[0] = 1;

		for (int i = 1; i < arr.length; i++) {
			answers.get(i).add(arr[i]);
			dp[i] = 1;

			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] > arr[j]) {

					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;

						answers.get(i).clear();

						for (int tmp : answers.get(j)) {
							answers.get(i).add(tmp);
						}
						answers.get(i).add(arr[i]);

					} else {

					}

				} else {

				}
			}

		}

		int max_index = 0;
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(max_index).size() < answers.get(i).size())
				max_index = i;
		}

		System.out.println(answers.get(max_index).size());
		for (int tmp : answers.get(max_index))
			System.out.print(tmp + " ");

	}
}
