import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int M;
		boolean[] breakdown = new boolean[10];

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				breakdown[Integer.parseInt(st.nextToken())] = true;
		}

		/////////////////////////////////////////////////////

		int min = -1;
		int max = -1;

		// N보다 작으면서 최대인 채널 (입력 가능한 최소 채널은 0)
		int num;
		for (num = N; num >= 0; num--) {
			boolean can = true;

			String tmp = String.valueOf(num);
			for (int i = 0; i < tmp.length(); i++) {
				if (breakdown[tmp.charAt(i) - '0'])
					can = false;
			}
			if (can) {
				min = num;
				break;
			}
		}

		// N보다 크면서 최소인 채널 (입력 가능한 최대 채널은 100만)(더 위로는 볼 필요가 없다)
		for (num = N; num <= 1000000-101; num++) {
			boolean can = true;

			String tmp = String.valueOf(num);
			for (int i = 0; i < tmp.length(); i++) {
				if (breakdown[tmp.charAt(i) - '0'])
					can = false;
			}
			if (can) {
				max = num;
				break;
			}
		}

		int min_answer;
		int max_answer;

		// 채널 못 찾았으면?
		if (min != -1)
			min_answer = String.valueOf(min).length() + N - min;
		else {
			min_answer = Integer.MAX_VALUE;
		}
		// 채널 못 찾았으면?
		if (max != -1)
			max_answer = String.valueOf(max).length() + max - N;
		else {
			max_answer = Integer.MAX_VALUE;
		}
		int directly_answer = Math.abs(100 - N);

		System.out.println(Math.min(Math.min(min_answer, max_answer), directly_answer));

	}

}
