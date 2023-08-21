import java.io.*; // 입출력 라이브러리 임포트
import java.util.*; // 유틸 라이브러리 임포트

class Person { // 사람 클래스 정의
	int y; // 행
	int x; // 열
	String direction; // 가고 있는 방향

	Person(int y, int x, String direction) { // 생성자
		this.y = y; // 행
		this.x = x; // 열
		this.direction = direction; // 방향
	}
}

public class Main { // 문제 3번 클래스
	public static void main(String[] args) throws IOException { // 메인 함수 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 초기화 할당
		StringBuilder sb = new StringBuilder(); // 출력 객체 초기화 할당
		StringTokenizer st; // 끊어 읽기 객체 초기화

		int R, C; // 행,열
		char arr[][]; // 맵

		st = new StringTokenizer(br.readLine()); // 한 줄 읽어
		R = Integer.parseInt(st.nextToken()); // 앞에꺼 정수형으로 바꿔 R
		C = Integer.parseInt(st.nextToken()); // 뒤에꺼 정수형으로 바꿔 C
		arr = new char[R][C]; // 맵 크기 할당

		for (int i = 0; i < R; i++) { // R번 돌아
			String input_line = br.readLine(); // 한줄 읽어
			for (int j = 0; j < C; j++) { // C번 돌아
				arr[i][j] = input_line.charAt(j); // 아까 읽은 한줄에서 C번 문자 맵에 저장
			}
		}

		////////////////////////////////////////////
		Person answer = null; // 정답은 없어 아직

		// 그냥 멍청하게 모든 지점에 문자 다 넣어보고 시뮬레이션 돌려봐
		for (int i = 0; i < R; i++) { // R번 실행
			for (int j = 0; j < C; j++) { // C번 실행
				if (arr[i][j] == '.') { // 점이 있는 위치면

					// 케이스 문자를 넣어보고 simulation이 true를 반환하면 해당 지점과 문자 뭐 넣었는지 기억하고 멈춰
					// 밑에는 케이스의 경우의 수들이다

					// case |
					arr[i][j] = '|';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case -
					arr[i][j] = '-';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case +
					arr[i][j] = '+';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case 1
					arr[i][j] = '1';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case 2
					arr[i][j] = '2';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case 3
					arr[i][j] = '3';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// case 4
					arr[i][j] = '4';
					if (simulation(arr)) {
						answer = new Person(i, j, String.valueOf(arr[i][j]));
						break;
					}

					// 에잇 못 찾았네 원상 복구해
					arr[i][j] = '.';
				}
			}
			// 뭐야 정답찾았네 그만 돌아
			if (answer != null)
				break;
		}

		sb.append(answer.y + 1).append(" ");
		sb.append(answer.x + 1).append(" ");
		sb.append(answer.direction).append(" ");
		sb.append("\n");

		// 전체 출력
		System.out.println(sb);
	}

	public static boolean simulation(char[][] arr) { // 시뮬레이션 돌려보자
		Person start = null; // 출발 지점
		Person end = null; // 도착 지점
		int[][] visited = new int[arr.length][arr[0].length]; // 해당 지점 방문 횟수

		for (int i = 0; i < arr.length; i++) { // 행 돌기
			for (int j = 0; j < arr[0].length; j++) { // 열 돌기
				if (arr[i][j] == 'M') // 출발 지점 찾았다
					start = new Person(i, j, null); // 출발 지점 할당
				if (arr[i][j] == 'Z') // 도착 지점 찾았다
					end = new Person(i, j, null); // 도착 지점 할당
			}
		}
		visited[start.y][start.x]++; // 최초 출발지 방문 횟수 ++;

		// 최초 출발지점에서 근처에 어디로 가야하는지 일단 한 번만 체크하자 방향성을 몰라서 그래
		// 위에 있네
		if (start.y - 1 >= 0 && (arr[start.y - 1][start.x] == '|' || arr[start.y - 1][start.x] == '+'
				|| arr[start.y - 1][start.x] == '1' || arr[start.y - 1][start.x] == '4'))
			start = new Person(start.y - 1, start.x, "UP");

		// 아래에 있네
		else if (start.y + 1 < arr.length && (arr[start.y + 1][start.x] == '|' || arr[start.y + 1][start.x] == '+'
				|| arr[start.y + 1][start.x] == '2' || arr[start.y + 1][start.x] == '3'))
			start = new Person(start.y + 1, start.x, "DOWN");

		// 왼쪽에 있네
		else if (start.x - 1 >= 0 && (arr[start.y][start.x - 1] == '-' || arr[start.y][start.x - 1] == '+'
				|| arr[start.y][start.x - 1] == '1' || arr[start.y][start.x - 1] == '2'))
			start = new Person(start.y, start.x - 1, "LEFT");

		// 오른쪽에 있네
		else if (start.x + 1 < arr[0].length && (arr[start.y][start.x+1] == '-' || arr[start.y ][start.x+ 1] == '+'
				|| arr[start.y][start.x + 1] == '3' || arr[start.y][start.x + 1] == '4'))
			start = new Person(start.y, start.x + 1, "RIGHT");

		// 무한대로 돌아봐
		while (true) {
			// 범위 밖으로 나갔네
			if (start.y < 0 || start.y >= arr.length || start.x < 0 || start.x >= arr[0].length)
				return false;

			// 이 지점 방문 횟수++;
			visited[start.y][start.x]++;

			// 도착했다면
			if (start.y == end.y && start.x == end.x) {
				// 모든 블럭 지나쳤는지 검사해
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[0].length; j++) {
						// 어 점이 아닌 블럭인데 내가 안갔었다고? 그럼 틀린 시뮬이야
						if (arr[i][j] != '.' && visited[i][j] == 0)
							return false;
						// 어 + 인데 두번 방문을 안했어? 그럼 틀린 시뮬이야
						if (arr[i][j] == '+' && visited[i][j] != 2)
							return false;
					}
				}
				// 모든 블록을 잘 썻네?
				return true;
			}

			// 점에 가면 틀렸어 그길은
			else if (arr[start.y][start.x] == '.') {
				return false;
			}

			// | 일 경우
			else if (arr[start.y][start.x] == '|') {
				if (start.direction.equals("DOWN")) // 진행 방향 아래이다
					start.y++;
				else if (start.direction.equals("UP")) // 진행 방향 위다
					start.y--;
				else
					return false;
			} else if (arr[start.y][start.x] == '-') { // - 일 경우
				if (start.direction.equals("LEFT")) // 진행 방향 왼쪽이다
					start.x--;
				else if (start.direction.equals("RIGHT")) // 진행방향 오른쪽이다
					start.x++;
				else
					return false;
			} else if (arr[start.y][start.x] == '+') { // + 일경우
				if (start.direction.equals("DOWN")) // 진행 방향 아래
					start.y++;
				else if (start.direction.equals("UP")) // 진행 방향 업
					start.y--;
				else if (start.direction.equals("LEFT")) // 진행 방향 왼쪽
					start.x--;
				else if (start.direction.equals("RIGHT")) // 진행 방향 오른쪽
					start.x++;
				else
					return false;
			} else if (arr[start.y][start.x] == '1') { // 1일 경우
				if (start.direction.equals("UP")) { // 진행방향 업
					start.direction = "RIGHT";
					start.x++;
				} else if (start.direction.equals("LEFT")) { // 진행 방향 왼쪽
					start.direction = "DOWN";
					start.y++;
				} else
					return false;
			} else if (arr[start.y][start.x] == '2') { // 2 일경우
				if (start.direction.equals("DOWN")) { // 진행방향 아래
					start.direction = "RIGHT";
					start.x++;
				} else if (start.direction.equals("LEFT")) { // 진행방향 왼쪽
					start.direction = "UP";
					start.y--;
				} else
					return false;
			} else if (arr[start.y][start.x] == '3') { // 3일 경우
				if (start.direction.equals("DOWN")) { // 진행방향 아래
					start.direction = "LEFT";
					start.x--;
				} else if (start.direction.equals("RIGHT")) { // 진행 방향 오른쪽
					start.direction = "UP";
					start.y--;
				} else
					return false;
			} else if (arr[start.y][start.x] == '4') { // 4일 경우
				if (start.direction.equals("UP")) { // 진행 방향 위
					start.direction = "LEFT";
					start.x--;
				} else if (start.direction.equals("RIGHT")) { // 진행 방향 오른쪽
					start.direction = "DOWN";
					start.y++;
				} else
					return false; // 틀렸어 없으면
			} else
				return false; // 내가 원하는 문자가 없으면 또 틀렸어
		}

	}
}