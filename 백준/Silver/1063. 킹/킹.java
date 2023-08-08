import java.io.*;
import java.util.*;

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		String input_king = st.nextToken();
		String input_rock = st.nextToken();
		int N = Integer.parseInt(st.nextToken());

		Point king = new Point(7 - (input_king.charAt(1) - '1'), input_king.charAt(0) - 'A');
		Point rock = new Point(7 - (input_rock.charAt(1) - '1'), input_rock.charAt(0) - 'A');

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			Point new_king = king;
			Point new_rock = rock;

			if (line.equals("R")) {
				new_king = new Point(king.y, king.x + 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y, rock.x + 1);
				}
			} else if (line.equals("L")) {
				new_king = new Point(king.y, king.x - 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y, rock.x - 1);
				}

			} else if (line.equals("B")) {
				new_king = new Point(king.y + 1, king.x);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y + 1, rock.x);
				}

			} else if (line.equals("T")) {
				new_king = new Point(king.y - 1, king.x);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y - 1, rock.x);
				}

			} else if (line.equals("RT")) {
				new_king = new Point(king.y - 1, king.x + 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y - 1, rock.x + 1);
				}

			} else if (line.equals("LT")) {
				new_king = new Point(king.y - 1, king.x - 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y - 1, rock.x - 1);
				}

			} else if (line.equals("RB")) {
				new_king = new Point(king.y + 1, king.x + 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y + 1, rock.x + 1);
				}

			} else if (line.equals("LB")) {
				new_king = new Point(king.y + 1, king.x - 1);

				// 같은 칸이다!
				if (new_king.y == rock.y && new_king.x == rock.x) {
					new_rock = new Point(rock.y + 1, rock.x - 1);
				}

			}

			if (new_king.y > 7 || new_king.y < 0)
				continue;
			else if (new_king.x > 7 || new_king.x < 0)
				continue;

			else if (new_rock.y > 7 || new_rock.y < 0)
				continue;
			else if (new_rock.x > 7 || new_rock.x < 0)
				continue;
			else {
				king = new_king;
				rock = new_rock;
			}

		}
//		
//		Point king = new Point(7 - (input_king.charAt(1) - '1'), input_king.charAt(0) - 'A');
//		Point rock = new Point(7 - (input_rock.charAt(1) - '1'), input_rock.charAt(0) - 'A');

//		king.y = 7 - (정답-'1');
		
		System.out.print((char) (king.x + 'A'));
		System.out.println((char) (7 - king.y + '1'));

		System.out.print((char) (rock.x + 'A'));
		System.out.println((char) (7 - rock.y + '1'));
		
		

	}
}