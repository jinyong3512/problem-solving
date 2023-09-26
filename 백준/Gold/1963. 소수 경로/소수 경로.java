import java.io.*;
import java.util.*;

class Data {
	int number, depth;

	Data(int number, int depth) {
		this.number = number;
		this.depth = depth;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int number1 = Integer.parseInt(st.nextToken());
			int number2 = Integer.parseInt(st.nextToken());

			Queue<Data> queue = new LinkedList<>();
			boolean[] visited = new boolean[10000];

			queue.add(new Data(number1, 0));
			visited[number1] = true;

			while (!queue.isEmpty()) {
				Data cur = queue.remove();

//				System.out.println(cur.number);

				if (cur.number == number2) {
					System.out.println(cur.depth);
					break;
				}

				// 첫 자리
				for (int i = 1; i <= 9; i++) {
					String result = String.valueOf(i) + String.valueOf(cur.number).substring(1);

					if (isSosu(Integer.parseInt(result)) && !visited[Integer.parseInt(result)]) {
						visited[Integer.parseInt(result)] = true;
						queue.add(new Data(Integer.parseInt(result), cur.depth + 1));
					}
				}

				// 둘 자리
				for (int i = 0; i <= 9; i++) {
					String result = String.valueOf(cur.number).charAt(0) + String.valueOf(i)
							+ String.valueOf(cur.number).substring(2);

					if (isSosu(Integer.parseInt(result)) && !visited[Integer.parseInt(result)]) {
						visited[Integer.parseInt(result)] = true;
						queue.add(new Data(Integer.parseInt(result), cur.depth + 1));
					}
				}

				// 셋 자리
				for (int i = 0; i <= 9; i++) {
					String result = String.valueOf(cur.number).substring(0, 2) + String.valueOf(i)
							+ String.valueOf(cur.number).substring(3);

					if (isSosu(Integer.parseInt(result)) && !visited[Integer.parseInt(result)]) {
						visited[Integer.parseInt(result)] = true;
						queue.add(new Data(Integer.parseInt(result), cur.depth + 1));
					}
				}

				// 넷 자리
				for (int i = 0; i <= 9; i++) {
					String result = String.valueOf(cur.number).substring(0, 3) + String.valueOf(i);

					if (isSosu(Integer.parseInt(result)) && !visited[Integer.parseInt(result)]) {
						visited[Integer.parseInt(result)] = true;
						queue.add(new Data(Integer.parseInt(result), cur.depth + 1));
					}
				}

			}
		}

	}

	public static boolean isSosu(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}
}
