import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

class Point {
	int y, x;
	int depth;
	int spare_jump;

	Point(int y, int x, int depth, int spare_jump) {
		this.y = y;
		this.x = x;
		this.depth = depth;
		this.spare_jump = spare_jump;
	}
}

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int K;
		int W, H;
		boolean[][] arr;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				if (st.nextToken().equals("1"))
					arr[i][j] = true;
			}
		}

		///////////////////////////////////////////////////

		Queue<Point> queue = new LinkedList<>();
		int[][] spare_jump_section = new int[arr.length][arr[0].length];

		for (int i = 0; i < spare_jump_section.length; i++) {
			for (int j = 0; j < spare_jump_section[0].length; j++)
				spare_jump_section[i][j] = -1;
		}

		Point start = new Point(0, 0, 0, K);
		if (start.spare_jump > spare_jump_section[0][0]) {
			spare_jump_section[0][0] = start.spare_jump;
			queue.add(new Point(0, 0, 0, K));
		}

		while (!queue.isEmpty()) {
			Point tmp = queue.remove();

			if (tmp.y == arr.length - 1 && tmp.x == arr[0].length - 1) {
				sb.append(tmp.depth);
				break;
			}

			// 위로
			if (tmp.y - 1 >= 0 && tmp.spare_jump > spare_jump_section[tmp.y - 1][tmp.x] && !arr[tmp.y - 1][tmp.x]) {
				spare_jump_section[tmp.y - 1][tmp.x] = tmp.spare_jump;
				queue.add(new Point(tmp.y - 1, tmp.x, tmp.depth + 1, tmp.spare_jump));
			}

			// 아래로
			if (tmp.y + 1 < arr.length && tmp.spare_jump > spare_jump_section[tmp.y + 1][tmp.x]
					&& !arr[tmp.y + 1][tmp.x]) {
				spare_jump_section[tmp.y + 1][tmp.x] = tmp.spare_jump;
				queue.add(new Point(tmp.y + 1, tmp.x, tmp.depth + 1, tmp.spare_jump));
			}

			// 오른쪽
			if (tmp.x + 1 < arr[0].length && tmp.spare_jump > spare_jump_section[tmp.y][tmp.x + 1]
					&& !arr[tmp.y][tmp.x + 1]) {
				spare_jump_section[tmp.y][tmp.x + 1] = tmp.spare_jump;
				queue.add(new Point(tmp.y, tmp.x + 1, tmp.depth + 1, tmp.spare_jump));
			}

			// 왼쪽
			if (tmp.x - 1 >= 0 && tmp.spare_jump > spare_jump_section[tmp.y][tmp.x - 1] && !arr[tmp.y][tmp.x - 1]) {
				spare_jump_section[tmp.y][tmp.x - 1] = tmp.spare_jump;
				queue.add(new Point(tmp.y, tmp.x - 1, tmp.depth + 1, tmp.spare_jump));
			}

			// 1
			if (tmp.y - 2 >= 0 && tmp.x + 1 < arr[0].length
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y - 2][tmp.x + 1] && !arr[tmp.y - 2][tmp.x + 1]) {
				spare_jump_section[tmp.y - 2][tmp.x + 1] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y - 2, tmp.x + 1, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 2
			if (tmp.y - 1 >= 0 && tmp.x + 2 < arr[0].length
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y - 1][tmp.x + 2] && !arr[tmp.y - 1][tmp.x + 2]) {
				spare_jump_section[tmp.y - 1][tmp.x + 2] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y - 1, tmp.x + 2, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 3
			if (tmp.y + 1 < arr.length && tmp.x + 2 < arr[0].length
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y + 1][tmp.x + 2] && !arr[tmp.y + 1][tmp.x + 2]) {
				spare_jump_section[tmp.y + 1][tmp.x + 2] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y + 1, tmp.x + 2, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 4
			if (tmp.y + 2 < arr.length && tmp.x + 1 < arr[0].length
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y + 2][tmp.x + 1] && !arr[tmp.y + 2][tmp.x + 1]) {
				spare_jump_section[tmp.y + 2][tmp.x + 1] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y + 2, tmp.x + 1, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 5
			if (tmp.y + 2 < arr.length && tmp.x - 1 >= 0
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y + 2][tmp.x - 1] && !arr[tmp.y + 2][tmp.x - 1]) {
				spare_jump_section[tmp.y + 2][tmp.x - 1] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y + 2, tmp.x - 1, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 6
			if (tmp.y + 1 < arr.length && tmp.x - 2 >= 0
					&& tmp.spare_jump - 1 > spare_jump_section[tmp.y + 1][tmp.x - 2] && !arr[tmp.y + 1][tmp.x - 2]) {
				spare_jump_section[tmp.y + 1][tmp.x - 2] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y + 1, tmp.x - 2, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 7
			if (tmp.y - 1 >= 0 && tmp.x - 2 >= 0 && tmp.spare_jump - 1 > spare_jump_section[tmp.y - 1][tmp.x - 2]
					&& !arr[tmp.y - 1][tmp.x - 2]) {
				spare_jump_section[tmp.y - 1][tmp.x - 2] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y - 1, tmp.x - 2, tmp.depth + 1, tmp.spare_jump - 1));
			}

			// 8
			if (tmp.y - 2 >= 0 && tmp.x - 1 >= 0 && tmp.spare_jump - 1 > spare_jump_section[tmp.y - 2][tmp.x - 1]
					&& !arr[tmp.y - 2][tmp.x - 1]) {
				spare_jump_section[tmp.y - 2][tmp.x - 1] = tmp.spare_jump - 1;
				queue.add(new Point(tmp.y - 2, tmp.x - 1, tmp.depth + 1, tmp.spare_jump - 1));
			}

		}

		if (sb.length() == 0)
			System.out.println("-1");
		else
			System.out.println(sb);

	}
}