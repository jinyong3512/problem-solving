import java.io.*;
import java.util.*;

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {

	public static boolean answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t;
		t = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= t; test_case++) {

			int n;
			Point start;
			Point[] con;
			Point end;

			//////////////////////////////////////////////////

			n = Integer.parseInt(br.readLine());
			con = new Point[n];

			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				con[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			//////////////////////////////////////////////////

			answer = false;

			// 순열이야
			// 순열 아니고 그냥 순회네
			boolean[] visited = new boolean[n];
			dfs(start, end, con, start, visited);

			if (answer)
				sb.append("happy").append("\n");
			else
				sb.append("sad").append("\n");

		}
		System.out.println(sb);

	}

	public static void dfs(Point start, Point end, Point[] con, Point cur, boolean[] visited) {

		if (Math.abs(cur.y - end.y) + Math.abs(cur.x - end.x) <= 20 * 50) {
			answer = true;
			return;
		}

		for (int i = 0; i < con.length; i++) {
			if (Math.abs(cur.y - con[i].y) + Math.abs(cur.x - con[i].x) <= 20 * 50 && !visited[i]) {
				visited[i] = true;
				dfs(start, end, con, new Point(con[i].y, con[i].x), visited);
			}
		}
	}
}
