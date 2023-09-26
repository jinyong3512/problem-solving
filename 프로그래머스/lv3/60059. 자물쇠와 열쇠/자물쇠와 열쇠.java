class Solution {

	public boolean solution(int[][] key, int[][] lock) {

		int[][] lock2 = new int[key.length + lock.length + key.length][key[0].length + lock[0].length + key[0].length];

		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock[0].length; j++) {
				lock2[key.length + i][key[0].length + j] = lock[i][j];
			}
		}

		for (int rotate_count = 1; rotate_count <= 4; rotate_count++) {
			key = rotate(key);

			if (check_answer(key, lock, lock2))
				return true;

		}

		return false;
	}

	public static int[][] rotate(int[][] key) {
		int[][] new_key = new int[key.length][key[0].length];

		for (int depth = 0; depth < (key.length + 1) / 2; depth++) {

			// 왼쪽거 위로 넣기
			for (int j = 0 + depth; j < key[0].length - depth; j++) {
				new_key[0 + depth][j] = key[key[0].length - 1 - j][0 + depth];
			}

			// 아래꺼 왼쪽에 넣기
			for (int i = 0 + depth; i < key.length - depth; i++) {
				new_key[i][0 + depth] = key[key.length - 1 - depth][i];
			}
			// 오른쪽꺼 아래 넣기
			for (int j = 0 + depth; j < key[0].length - depth; j++) {
				new_key[key.length - 1 - depth][j] = key[key[0].length - 1 - j][key.length - 1 - depth];
			}

			// 위에꺼 오른쪽에 넣기
			for (int i = 0 + depth; i < key.length; i++) {
				new_key[i][key[0].length - 1 - depth] = key[0 + depth][i];
			}
		}
		return new_key;
	}

	public static boolean check_answer(int[][] key, int[][] lock, int[][] lock2) {
		for (int i = 0; i < lock2.length - key.length + 1; i++) {
			for (int j = 0; j < lock2[0].length - key[0].length + 1; j++) {

				for (int y = 0; y < key.length; y++) {
					for (int x = 0; x < key[0].length; x++) {
						lock2[i + y][j + x] += key[y][x];
					}
				}

				boolean can = true;

				for (int y = 0; y < lock.length; y++) {
					for (int x = 0; x < lock[0].length; x++) {
						if (lock2[key.length + y][key[0].length + x] != 1) {
							can = false;
						}
					}
				}

				for (int y = 0; y < key.length; y++) {
					for (int x = 0; x < key[0].length; x++) {
						lock2[i + y][j + x] -= key[y][x];
					}
				}

				if (can)
					return true;

			}
		}

		return false;
	}
}