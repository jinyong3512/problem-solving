import java.io.*;
import java.util.*;

class Robot {
	int index;
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, K;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[2 * N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		/////////////////////////////////////////////////

		// 0이 올리는 위치 // N-1이 내리는 위치
		// 0 index 로봇이 틀딱 로봇
		ArrayList<Robot> robots = new ArrayList<>();

		int durability_count = 0;
		int answer = 0;

		while (true) {
			answer++;
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			rotate(arr);
			for (int i = 0; i < robots.size(); i++) {
				robots.get(i).index++;
				if (robots.get(i).index == N - 1) {
					robots.remove(i);
					i--;
				}
			}

			// 2. 가장 먼저 벨트에 올라간 로봇부터,
			// 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			for (int i = 0; i < robots.size(); i++) {

				// 앞칸에 내구도가 있어
				if (arr[robots.get(i).index + 1] > 0) {
					// 내가 최초 로봇이다! || 내 앞에 로봇이 있는데 ㄱㅊ
					if (i == 0 || robots.get(i).index + 1 != robots.get(i - 1).index) {
						robots.get(i).index++;
						arr[robots.get(i).index]--;
						if (arr[robots.get(i).index] == 0) {
							durability_count++;
						}

					}
				}

				// 이동 시켰더니 막칸이네 넌 내려가라
				if (robots.get(i).index == N - 1) {
					robots.remove(i);
					i--;
				}
			}

			if (arr[0] != 0) {
				robots.add(new Robot());
				arr[0]--;
				if (arr[0] == 0)
					durability_count++;
			}

			if (durability_count >= K)
				break;
		}

		System.out.println(answer);

	}

	public static void rotate(int[] arr) {
		int now;
		int prev = arr[0];
		arr[0] = arr[arr.length - 1];
		for (int i = 0; i < arr.length - 1; i++) {
			now = arr[i + 1];
			arr[i + 1] = prev;
			prev = now;
		}

	}
}