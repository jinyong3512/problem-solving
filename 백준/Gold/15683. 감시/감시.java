import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

class Cctv {
	int y, x;
	int number;

	Cctv(int y, int x, int number) {
		this.y = y;
		this.x = x;
		this.number = number;
	}

}

public class Main { // 메인 클래스

	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		////////////////////////////////////////////////////

		ArrayList<Cctv> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && arr[i][j] != 6)
					arrayList.add(new Cctv(i, j, arr[i][j]));
			}
		}

		recursion(arr, arrayList, 0);

		System.out.println(answer);

	}

	public static void recursion(int[][] arr, ArrayList<Cctv> arrayList, int index) {
		if (index == arrayList.size()) {
			int number_0 = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++)
					if (arr[i][j] == 0)
						number_0++;
			}
			answer = Math.min(answer, number_0);

//			for (int i = 0; i < arr.length; i++) {
//				for (int j = 0; j < arr[0].length; j++)
//					System.out.print(arr[i][j] + " ");
//				System.out.println("");
//			}
//			System.out.println("");

			return;
		}

		int[][] new_arr;

		if (arrayList.get(index).number == 1) {
			// 상
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 하
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 좌
			new_arr = make_clone(arr);
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 우
			new_arr = make_clone(arr);
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

		}

		else if (arrayList.get(index).number == 2) {
			// 상하
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 좌우
			new_arr = make_clone(arr);
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
		} else if (arrayList.get(index).number == 3) {
			// 상우
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 상좌
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
			// 하우
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
			// 하좌
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}

			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
		} else if (arrayList.get(index).number == 4) {
			// 좌상우
			new_arr = make_clone(arr);
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
			// 상우하
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
			// 우하좌
			new_arr = make_clone(arr);
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);

			// 하좌상
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
		} else {
			// 상하좌우
			new_arr = make_clone(arr);
			for (int y = arrayList.get(index).y - 1; y >= 0; y--) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int y = arrayList.get(index).y + 1; y < arr.length; y++) {
				if (new_arr[y][arrayList.get(index).x] == 6) {
					break;
				} else if (new_arr[y][arrayList.get(index).x] == 0) {
					new_arr[y][arrayList.get(index).x] = -1;
				}
			}
			for (int x = arrayList.get(index).x - 1; x >= 0; x--) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			for (int x = arrayList.get(index).x + 1; x < arr[0].length; x++) {
				if (new_arr[arrayList.get(index).y][x] == 6) {
					break;
				} else if (new_arr[arrayList.get(index).y][x] == 0) {
					new_arr[arrayList.get(index).y][x] = -1;
				}
			}
			recursion(new_arr, arrayList, index + 1);
		}
	}

	public static int[][] make_clone(int[][] arr) {
		int[][] new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[i][j] = arr[i][j];
			}
		}
		return new_arr;
	}
}