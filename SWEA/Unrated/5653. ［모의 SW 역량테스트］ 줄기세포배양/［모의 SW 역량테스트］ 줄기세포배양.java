import java.io.*;
import java.util.*;

class Data {
	int value;
	int time;

	Data(int value, int time) {
		this.value = value;
		this.time = time;
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N, M, K;
			Data[][] arr;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new Data[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					arr[i][j] = new Data(value, value * 2);
				}
			}

			//////////////////////////////////////////////////////////

			int time = 0;
			while (time < K) {
				Data[][] new_arr = new Data[arr.length + 2][arr[0].length + 2];

				for (int i = 0; i < new_arr.length; i++) {
					for (int j = 0; j < new_arr[0].length; j++) {
						new_arr[i][j] = new Data(0, 0);
					}
				}

				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[0].length; j++) {
						// 아무것도 없는 곳
						if (arr[i][j].value == 0) {

						}
						// 뭔가 있어
						else {
							// 대기 상태 인거
							if (arr[i][j].time > arr[i][j].value) {
								arr[i][j].time--;
								new_arr[i + 1][j + 1] = arr[i][j];
							}
							// 살아 있는 거
							else if (arr[i][j].time > 0) {
								arr[i][j].time--;
								new_arr[i + 1][j + 1] = arr[i][j];

								// 위로 번식
								if (i == 0 || arr[i - 1][j].value == 0) {
									if (arr[i][j].value > new_arr[i + 1 - 1][j + 1].value)
										new_arr[i + 1 - 1][j + 1] = new Data(arr[i][j].value, arr[i][j].value * 2);
								}

								// 아래로 번식
								if (i == arr.length - 1 || arr[i + 1][j].value == 0) {
									if (arr[i][j].value > new_arr[i + 1 + 1][j + 1].value)
										new_arr[i + 1 + 1][j + 1] = new Data(arr[i][j].value, arr[i][j].value * 2);
								}

								// 왼쪽으로 번식
								if (j == 0 || arr[i][j - 1].value == 0) {
									if (arr[i][j].value > new_arr[i + 1][j + 1 - 1].value)
										new_arr[i + 1][j + 1 - 1] = new Data(arr[i][j].value, arr[i][j].value * 2);
								}

								// 오른쪽으로 번식
								if (j == arr[0].length - 1 || arr[i][j + 1].value == 0) {
									if (arr[i][j].value > new_arr[i + 1][j + 1 + 1].value)
										new_arr[i + 1][j + 1 + 1] = new Data(arr[i][j].value, arr[i][j].value * 2);
								}
							}
							// 죽은 거
							else {
								new_arr[i + 1][j + 1] = arr[i][j];
							}
						}
					}
				}
				arr = new_arr;

				arr = func1(arr);
				arr = func2(arr);
				arr = func3(arr);
				arr = func4(arr);

				time++;
			}

			int answer = 0;

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j].time > 0)
						answer++;
				}
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);

	}
	
	public static Data[][] func1(Data[][] arr) {
		boolean can;
		Data[][] new_arr = null;

		can = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0].value != 0)
				can = false;
		}
		if (can) {
			new_arr = new Data[arr.length][arr[0].length - 1];
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length; j++) {
					new_arr[i][j] = arr[i][j + 1];
				}
			}
			return new_arr;
		}
		
		return arr;
	}
	public static Data[][] func2(Data[][] arr) {
		boolean can;
		Data[][] new_arr = null;

		can = true;
		for (int j = 0; j < arr[0].length; j++) {
			if (arr[0][j].value != 0)
				can = false;
		}
		if (can) {
			new_arr = new Data[arr.length-1][arr[0].length];
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length; j++) {
					new_arr[i][j] = arr[i+1][j];
				}
			}
			return new_arr;
		}
		
		return arr;
	}
	public static Data[][] func3(Data[][] arr) {
		boolean can;
		Data[][] new_arr = null;

		can = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][arr[0].length-1].value != 0)
				can = false;
		}
		if (can) {
			new_arr = new Data[arr.length][arr[0].length - 1];
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length; j++) {
					new_arr[i][j] = arr[i][j];
				}
			}
			return new_arr;
		}
		
		return arr;
	}
	public static Data[][] func4(Data[][] arr) {
		boolean can;
		Data[][] new_arr = null;

		can = true;
		for (int j = 0; j < arr[0].length; j++) {
			if (arr[arr.length-1][j].value != 0)
				can = false;
		}
		if (can) {
			new_arr = new Data[arr.length-1][arr[0].length];
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length; j++) {
					new_arr[i][j] = arr[i][j];
				}
			}
			return new_arr;
		}
		
		return arr;
	}
}