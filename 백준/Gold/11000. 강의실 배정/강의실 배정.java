import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		////////////////////////////////////////////////////

		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o2[0] > o1[0])
					return -1;
				else if (o2[0] == o1[0])
					return 0;
				else
					return 1;
			}
		});

		int answer = 0;
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			if (pQ.isEmpty()) {
				pQ.add(arr[i][1]);
			} else {
				while (!pQ.isEmpty()) {
					if (pQ.peek() <= arr[i][0])
						pQ.remove();
					else
						break;
				}
				pQ.add(arr[i][1]);
			}

			answer = Math.max(pQ.size(), answer);
		}

		System.out.println(answer);

	}
}