import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

// 1위 2아래 3오른쪽 4왼쪽
class Shark {
	int y, x;
	int speed;
	int direction;
	int size;

	Shark(int y, int x, int speed, int direction, int size) {
		this.y = y;
		this.x = x;
		this.speed = speed;
		this.direction = direction;
		this.size = size;
	}
}

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int R, C, M;
		HashMap<Integer, Shark> sharks;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharks = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int y, x, speed, direction, size;
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			speed = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());

			sharks.put(y * C + x, new Shark(y, x, speed, direction, size));
		}

		/////////////////////////////////////////////////////////////////////////////////

		long answer = 0;
		for (int time = 0; time < C; time++) {

			int catch_shark = Integer.MAX_VALUE;

			for (int key : sharks.keySet()) {
				if (time == key % C) {
					if (catch_shark / C > key / C) {
						catch_shark = key;
					}
				}
			}

			if (catch_shark != Integer.MAX_VALUE) {
				answer += sharks.get(catch_shark).size;
				sharks.remove(catch_shark);
			}

			HashMap<Integer, Shark> new_sharks = new HashMap<>();

			for (int key : sharks.keySet()) {
				Shark shark = sharks.get(key);

				for (int time2 = 0; time2 < shark.speed; time2++) {

					// 위
					if (shark.direction == 1) {
						shark.y--;
						if (shark.y == -1) {
							shark.y += 2;
							shark.direction = 2;
						}
					}

					// 아래
					else if (shark.direction == 2) {
						shark.y++;
						if (shark.y == R) {
							shark.y -= 2;
							shark.direction = 1;
						}
					}

					// 오른쪽
					else if (shark.direction == 3) {
						shark.x++;
						if (shark.x == C) {
							shark.x -= 2;
							shark.direction = 4;
						}
					}
					// 왼쪽
					else if (shark.direction == 4) {
						shark.x--;
						if (shark.x == -1) {
							shark.x += 2;
							shark.direction = 3;
						}
					}

				}

				if (new_sharks.get(shark.y * C + shark.x) == null) {
					new_sharks.put(shark.y * C + shark.x, shark);
				} else {
					if (new_sharks.get(shark.y * C + shark.x).size < shark.size) {
						new_sharks.put(shark.y * C + shark.x, shark);
					}
				}
			}

			sharks = new_sharks;

		}

		System.out.println(answer);
	}
}