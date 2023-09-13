import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, P;
		int[][] order;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		order = new int[N][2];

		for (int i = 0; i < order.length; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
		}

		////////////////////////////////////////////////////

		ArrayList<Stack<Integer>> stacks = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			stacks.add(new Stack<Integer>());
		}

		int answer = 0;
		for (int i = 0; i < order.length; i++) {
			while (!stacks.get(order[i][0]).isEmpty()) {
				if (stacks.get(order[i][0]).peek() <= order[i][1])
					break;
				else {
					stacks.get(order[i][0]).pop();
					answer++;
				}
			}

			if (!stacks.get(order[i][0]).isEmpty() && stacks.get(order[i][0]).peek() == order[i][1]) {

			} else {
				stacks.get(order[i][0]).add(order[i][1]);
				answer++;
			}
		}
		System.out.println(answer);

	}
}