import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] eaten = new int[D + 1];
		int max = 1;
		eaten[C] = 1;
		int[] table = new int[N + K - 1];
		for (int i = 0; i < N; i++) {
			table[i] = Integer.parseInt(br.readLine().trim());
		}
		for (int i = 0; i < K - 1; i++) {
			table[N + i] = table[i];
		}

		for (int i = 0; i < K; i++) {
			if (eaten[table[i]] == 0) {
				max++;
			}
			eaten[table[i]]++;
		}
		int s = 0;
		int tmp = max;
		for (int i = K; i < N + K - 1; i++) {

			if (eaten[table[i]] == 0) {
				tmp++;

			}
			eaten[table[i]]++;
			if (eaten[table[s]] == 1) {
				tmp--;
			}
			eaten[table[s]]--;
			s++;
			max = Math.max(max, tmp);
		}
		System.out.println(max);

	}

}