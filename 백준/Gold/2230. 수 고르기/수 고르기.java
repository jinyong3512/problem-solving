import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////

		Arrays.sort(arr);

		int left = 0;
		int right = 1;
		int answer = Integer.MAX_VALUE;

		while (right < N) {
			int gap = arr[right] - arr[left];

			if (gap >= M) {
				answer = Math.min(gap, answer);
				left++;
				if (left == right)
					right++;
			} else
				right++;
		}

		System.out.println(answer);

	}

}
