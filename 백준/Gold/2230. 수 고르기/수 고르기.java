import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}
		int s = 0, e = 0;
		Arrays.sort(arr);
		int ans = Integer.MAX_VALUE;
		// 투 포인터 알고리즘
		while (s < N && e < N) {
			if (arr[e] - arr[s] < M) {
				e++;
				continue;
			}

			if (arr[e] - arr[s] == M) {
				ans = M;
				break;
			}

			ans = Math.min(ans, arr[e] - arr[s]);
			s++;
		}
		System.out.println(ans);

	}

}