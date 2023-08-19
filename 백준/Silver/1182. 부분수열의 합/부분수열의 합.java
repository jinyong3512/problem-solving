import java.io.*;
import java.util.*;

public class Main {

	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, S;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		////////////////////////////////////

		// 조합인데 

		boolean visited[] = new boolean[N];
		recursion(arr, S, -1, visited, 0, 0);
		System.out.println(answer);
	}

	public static void recursion(int[] arr, int S, int last_pick, boolean[] visited, int sum, int depth) {

		if (sum == S && depth!=0)
			answer++;

		
		for (int i = last_pick + 1; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursion(arr, S, i, visited, sum + arr[i],depth+1);
				visited[i] = false;
			}
		}
		

	}
}