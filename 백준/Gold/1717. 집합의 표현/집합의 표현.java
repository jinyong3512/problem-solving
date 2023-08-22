import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, m;
		int[] parents;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n + 1];

		/////////////////////////////////////////////

		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (order == 0) {
				union(parents,a,b);
			} else {
				if(find_parent(parents,a) == find_parent(parents,b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}

		}

	}

	public static void union(int[] parents, int a, int b) {
		int a_parent = find_parent(parents, a);
		int b_parent = find_parent(parents, b);

		if (a_parent > b_parent) {
			parents[a_parent] = b_parent;
		} else
			parents[b_parent] = a_parent;
	}

	public static int find_parent(int[] parents, int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find_parent(parents, parents[a]);
	}
}