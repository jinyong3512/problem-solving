// 

// io와 util 라이브러리 import
import java.io.*;
import java.util.*;

// 실행 클래스
class Main {
	public static void main(String[] args) throws IOException {
		// 입출력 변수들 선언 맨날 씀 (고정)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 배경
		boolean[][] black_paper = new boolean[100][100];

		// 검은색 종이 수
		int N = Integer.parseInt(br.readLine());

		// N번 돌기
		for (int i = 0; i < N; i++) {
			// 한줄 읽기
			st = new StringTokenizer(br.readLine());

			// 왼쪽 차이, 바닥 차이
			int left_gap = Integer.parseInt(st.nextToken());
			int bottom_gap = Integer.parseInt(st.nextToken());

			// 검은 종이 있는 부분은 true로 변경하기!
			for (int y = bottom_gap; y < bottom_gap + 10; y++) {
				for (int x = left_gap; x < left_gap + 10; x++) {
					black_paper[y][x] = true;
				}
			}
		}

		// 넓이 구하자
		int answer = 0;
		
		// 다 돌면서 black인 부분은 answer++;
		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if (black_paper[y][x])
					answer++;
			}
		}
		
		// 정답 출력
		System.out.println(answer);

	}
}