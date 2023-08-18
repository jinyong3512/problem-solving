import java.io.*;
import java.util.*;

//

class Main {
	public static int answer = 0; // 최댓값으로 계속 업데이트 할 정답

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int R, C;
		char[][] arr;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];

		for (int i = 0; i < R; i++) {
			String input_line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = input_line.charAt(j);
			}
		}

		////////////////////////////////////////////////////

		HashMap<Character, Boolean> hashMap = new HashMap<>();
		hashMap.put(arr[0][0], true);
		recursion(arr, hashMap, 1, 0, 0);
		
		System.out.println(answer);

	}

	public static void recursion(char[][] arr, HashMap<Character, Boolean> hashMap, int depth, int y, int x) {
		answer = Math.max(answer, depth);

		// 위로
		if (y - 1 >= 0 && !hashMap.getOrDefault(arr[y - 1][x], false)) {
			hashMap.put(arr[y - 1][x], true);
			recursion(arr, hashMap, depth + 1, y - 1, x);
			hashMap.remove(arr[y - 1][x]);
		}

		// 아래로
		if (y + 1 < arr.length && !hashMap.getOrDefault(arr[y + 1][x], false)) {
			hashMap.put(arr[y + 1][x], true);
			recursion(arr, hashMap, depth + 1, y + 1, x);
			hashMap.remove(arr[y + 1][x]);
		}

		// 왼쪽
		if (x - 1 >= 0 && !hashMap.getOrDefault(arr[y][x - 1], false)) {
			hashMap.put(arr[y][x - 1], true);
			recursion(arr, hashMap, depth + 1, y, x - 1);
			hashMap.remove(arr[y][x - 1]);
		}

		// 오른쪽
		if (x + 1 < arr[0].length && !hashMap.getOrDefault(arr[y][x + 1], false)) {
			hashMap.put(arr[y][x + 1], true);
			recursion(arr, hashMap, depth + 1, y, x + 1);
			hashMap.remove(arr[y][x + 1]);
		}
	}

}