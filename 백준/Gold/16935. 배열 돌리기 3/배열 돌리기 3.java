import java.io.*;
import java.util.*;
//	84348KB	936ms


// 실행 클래스
class Main {

	// N 세로 길이
	// M 가로 길이
	// R 주문 횟수
	public static int N, M, R;
	// arr 숫자 저장 배열
	public static int[][] arr;

	public static void main(String[] args) throws IOException {

		// 입출력 변수들 선언 맨날 씀 (고정)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 한 줄 읽고 N M R 쪼개서 정수형으로 저장
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// arr 배열을 세로 길이 N 과 가로 길이 M 으로 초기화
		arr = new int[N][M];

		// 한 줄씩 읽으며 한 칸씩 쪼개며 arr[i][j] 에 담아주기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		///////////////////////////////////////////////////////////////////////

		// 주문 라인 st로 받기
		st = new StringTokenizer(br.readLine());
		// R번 쪼개서 읽을 예정
		for (int i = 0; i < R; i++) {
			// 각 주문 숫자
			String number = st.nextToken();

			// 주문에 맞게 함수 호출
			if (number.contentEquals("1"))
				func1();
			else if (number.contentEquals("2"))
				func2();
			else if (number.contentEquals("3"))
				func3();
			else if (number.contentEquals("4"))
				func4();
			else if (number.contentEquals("5"))
				func5();
			else if (number.contentEquals("6"))
				func6();
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
//				System.out.print(arr[i][j] + " ");
				sb.append(arr[i][j]).append(" ");
			}
//			System.out.println();
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

	public static void func1() {
		// 덮어 씌어지는 영역만 저장하기 귀찮으니 그냥 새 배열에 다 담아버리자
		int[][] new_arr = new int[N][M];

		// 기존 배열 맨 왼쪽 맨 위 → ↓
		// 새 배열 맨 왼쪽 맨아래에서 → ↑ 하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[N - 1 - i][j] = arr[i][j];
			}
		}

		// 새 배열 데이터를 내 원래 변수로 옮기기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func2() {
		// 덮어 씌어지는 영역만 저장하기 귀찮으니 그냥 새 배열에 다 담아버리자
		int[][] new_arr = new int[N][M];

		// 기존의 배열을 ↓ → 읽고
		// 새 배열의 맨오른쪽에서 ↓ ← 넣기
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				new_arr[i][M - 1 - j] = arr[i][j];
			}
		}
		// 새 배열 데이터를 내 원래 변수로 옮기기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func3() {
		// 새배열 만들기
		int[][] new_arr = new int[M][N];

		// 기존 배열 맨 왼쪽 맨 위 → ↓ 읽기
		// 새 배열 맨오른쪽 맨 위에서 ↓ ← 넣기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[j][N - 1 - i] = arr[i][j];
			}
		}

		// 가로 세로가 바뀌어서 초기화
		arr = new int[M][N];

		// 내껄로 복사
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}

		// 배열의 크기가 바뀌었으니 N은 세로 길이 M은 가로 길이 컨셉 유지 하기 위해 변경
		int tmp = M;
		M = N;
		N = tmp;
	}

	public static void func4() {
		// 새배열 만들기
		int[][] new_arr = new int[M][N];

		// 기존 배열 맨왼쪽 맨위 → ↓
		// 새배열 맨왼쪽 아래에서 ↑ →
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[M - 1 - j][i] = arr[i][j];
			}
		}

		// 가로 세로가 바뀌어서 초기화
		arr = new int[M][N];

		// 내껄로 복사
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}

		// 배열의 크기가 바뀌었으니 N은 세로 길이 M은 가로 길이 컨셉 유지 하기 위해 변경
		int tmp = M;
		M = N;
		N = tmp;
	}

	public static void func5() {

		// 새 배열 만들기
		int[][] new_arr = new int[N][M];

		// 1 -> 2
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i][j + M / 2] = arr[i][j];
			}
		}

		// 2 -> 3
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i + N / 2][j] = arr[i][j];
			}
		}

		// 3 -> 4
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i][j - M / 2] = arr[i][j];
			}
		}

		// 4 -> 1
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i - N / 2][j] = arr[i][j];
			}
		}

		// 기존 배열로 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}

	public static void func6() {
		// 새 배열 만들기
		int[][] new_arr = new int[N][M];

		// 1 -> 4
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i + N / 2][j] = arr[i][j];
			}
		}

		// 2 -> 1
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i][j - M / 2] = arr[i][j];
			}
		}

		// 3 -> 2
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				new_arr[i - N / 2][j] = arr[i][j];
			}
		}

		// 4 -> 3
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				new_arr[i][j + M / 2] = arr[i][j];
			}
		}

		// 기존 배열로 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = new_arr[i][j];
			}
		}
	}
}