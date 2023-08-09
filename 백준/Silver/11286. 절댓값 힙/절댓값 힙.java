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

		// 연산의 갯수
		int N = Integer.parseInt(br.readLine());

		// 우선 순위 큐인데 재 정의 한다
		// 절댓값이 작을 수록 빨리 나오게 정렬하고
		// 같은 절댓값 이라면 음수가 먼저 나오게 정렬한다.
		PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) > Math.abs(o2))
					return 1;
				else if (Math.abs(o1) == Math.abs(o2)) {
					if (o1 < o2) {
						return -1;
					} else if (o1 == o2) {
						return 0;
					} else {
						return 1;
					}
				} else
					return -1;
			}
		});

		// N번 하기
		for (int i = 0; i < N; i++) {
			// 한줄 읽기
			String input = br.readLine();
			// 0이 들어온 경우
			if (input.contentEquals("0")) {
				// pQ가 비었을 경우
				if (pQ.isEmpty()) {
					System.out.println("0");
				}
				// 안비었따
				else {
					System.out.println(pQ.remove());
				}
			}
			// 0이 아닌 경우
			else {
				// pQ에 넣어!
				pQ.add(Integer.parseInt(input));
			}
		}

	}
}