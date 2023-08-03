import java.io.*;
import java.util.*;

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static int n;
	public static Point start;
	public static Point[] stores;
	public static Point end;

	public static Queue<Point> queue;
	public static boolean[] visited;
	public static boolean success;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int t = 0; t < test_case; t++) {
			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			stores = new Point[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				stores[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			//////////////////////////////////////////////////////////////////////////////////////////////

			queue = new LinkedList<>();
			queue.add(start);

			visited = new boolean[n];
			success = false;

			while (!queue.isEmpty()) {
				Point point = queue.remove();

				if (Math.abs(end.x - point.x) + Math.abs(end.y - point.y) <= 20 * 50) {
					success = true;
					break;
				}

				for (int i = 0; i < n; i++) {
					Point store = stores[i];
					if (Math.abs(store.x - point.x) + Math.abs(store.y - point.y) <= 20 * 50 && !visited[i]) {
						visited[i]=true;
						queue.add(store);
					}
				}

			}

			if (success)
				System.out.println("happy");
			else
				System.out.println("sad");

		}

	}
}