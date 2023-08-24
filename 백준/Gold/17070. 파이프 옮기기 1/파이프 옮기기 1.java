import java.io.*;
import java.util.*;

class Point {
	int y;
	int x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Pipe {
	Point head;
	Point tail;
	String direction;

	Pipe(Point head, Point tail, String direction) {
		this.head = head;
		this.tail = tail;
		this.direction = direction;
	}
}

public class Main {

	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("0"))
					map[i][j] = false;
				else
					map[i][j] = true;
			}
		}

		///////////////////////////////////////////

		Pipe pipe = new Pipe(new Point(0, 1), new Point(1, 1), "horizontal");
		recursion(map, pipe);
		System.out.println(answer);

	}

	public static void recursion(boolean[][] map, Pipe pipe) {

		if (pipe.head.y == map.length - 1 && pipe.head.x == map[0].length - 1) {
			answer++;
			return;
		}

		// 가로 방향
		if (pipe.direction.equals("horizontal")) {
			// 가로로 보내기
			if (pipe.head.x + 1 < map[0].length && !map[pipe.head.y][pipe.head.x + 1])
				recursion(map, new Pipe(new Point(pipe.head.y, pipe.head.x + 1),
						new Point(pipe.tail.y, pipe.tail.x + 1), "horizontal"));
			// 대각선 보내기
			if (pipe.head.y + 1 < map.length && pipe.head.x + 1 < map[0].length && !map[pipe.head.y + 1][pipe.head.x]
					&& !map[pipe.head.y][pipe.head.x + 1] && !map[pipe.head.y + 1][pipe.head.x + 1])
				recursion(map, new Pipe(new Point(pipe.head.y + 1, pipe.head.x + 1),
						new Point(pipe.tail.y, pipe.tail.x + 1), "diagonal"));
		}

		// 세로 방향
		else if (pipe.direction.equals("vertical")) {
			// 세로로 보내기
			if (pipe.head.y + 1 < map.length && !map[pipe.head.y + 1][pipe.head.x])
				recursion(map, new Pipe(new Point(pipe.head.y + 1, pipe.head.x),
						new Point(pipe.tail.y + 1, pipe.tail.x), "vertical"));
			// 대각선 보내기
			if (pipe.head.y + 1 < map.length && pipe.head.x + 1 < map[0].length && !map[pipe.head.y + 1][pipe.head.x]
					&& !map[pipe.head.y][pipe.head.x + 1] && !map[pipe.head.y + 1][pipe.head.x + 1])
				recursion(map, new Pipe(new Point(pipe.head.y + 1, pipe.head.x + 1),
						new Point(pipe.tail.y + 1, pipe.tail.x), "diagonal"));
		}

		// 대각선 방향
		else if (pipe.direction.equals("diagonal")) {
			// 가로로 보내기
			if (pipe.head.x + 1 < map[0].length && !map[pipe.head.y][pipe.head.x + 1])
				recursion(map, new Pipe(new Point(pipe.head.y, pipe.head.x + 1),
						new Point(pipe.tail.y + 1, pipe.tail.x + 1), "horizontal"));
			// 세로로 보내기
			if (pipe.head.y + 1 < map.length && !map[pipe.head.y + 1][pipe.head.x])
				recursion(map, new Pipe(new Point(pipe.head.y + 1, pipe.head.x),
						new Point(pipe.tail.y + 1, pipe.tail.x + 1), "vertical"));
			// 대각선 보내기
			if (pipe.head.y + 1 < map.length && pipe.head.x + 1 < map[0].length && !map[pipe.head.y + 1][pipe.head.x]
					&& !map[pipe.head.y][pipe.head.x + 1] && !map[pipe.head.y + 1][pipe.head.x + 1])
				recursion(map, new Pipe(new Point(pipe.head.y + 1, pipe.head.x + 1),
						new Point(pipe.tail.y + 1, pipe.tail.x+1), "diagonal"));
		}
	}
}