import java.io.*;
import java.util.*;

//

class AP {
	int index;
	int y, x;
	int range;
	int volume;

	AP(int index, int x, int y, int range, int volume) {
		this.index = index;
		this.y = y;
		this.x = x;
		this.range = range;
		this.volume = volume;
	}
}

class Person {
	int y, x;

	Person(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int T = Integer.parseInt(br.readLine()); // 한줄 읽고 정수형 변환 테케 갯수
		for (int test_case = 1; test_case <= T; test_case++) { // 테케만큼 돌기

			int move_time, ap_num;
			st = new StringTokenizer(br.readLine());
			move_time = Integer.parseInt(st.nextToken());
			ap_num = Integer.parseInt(st.nextToken());

			int[] person_a_move = new int[move_time];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < move_time; i++)
				person_a_move[i] = Integer.parseInt(st.nextToken());

			int[] person_b_move = new int[move_time];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < move_time; i++)
				person_b_move[i] = Integer.parseInt(st.nextToken());

			AP[] aps = new AP[ap_num];
			for (int i = 0; i < ap_num; i++) {
				st = new StringTokenizer(br.readLine());
				aps[i] = new AP(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			/////////////////////////////////////////////////////////////

			int answer = 0;

			Person person_a = new Person(1, 1);
			Person person_b = new Person(10, 10);

			for (int t = 0; t <= move_time; t++) {
				
				
				PriorityQueue<AP> person_a_ap = new PriorityQueue<>(new Comparator<AP>() {
					@Override
					public int compare(AP o1, AP o2) {
						if (o1.volume > o2.volume)
							return -1;
						else if (o1.volume == o2.volume)
							return 0;
						else
							return 1;
					}
				});
				PriorityQueue<AP> person_b_ap = new PriorityQueue<>(new Comparator<AP>() {
					@Override
					public int compare(AP o1, AP o2) {
						if (o1.volume > o2.volume)
							return -1;
						else if (o1.volume == o2.volume)
							return 0;
						else
							return 1;
					}
				});

				for (int i = 0; i < ap_num; i++) {
					if (Math.abs(aps[i].y - person_a.y) + Math.abs(aps[i].x - person_a.x) <= aps[i].range) {
						person_a_ap.add(aps[i]);
					}
					if (Math.abs(aps[i].y - person_b.y) + Math.abs(aps[i].x - person_b.x) <= aps[i].range) {
						person_b_ap.add(aps[i]);
					}
				}

				if (person_a_ap.isEmpty() && person_b_ap.isEmpty()) {

				} else if (person_a_ap.isEmpty()) {
					answer += person_b_ap.peek().volume;
				} else if (person_b_ap.isEmpty()) {
					answer += person_a_ap.peek().volume;
				} else {
					if (person_a_ap.size() == 1 && person_b_ap.size() == 1) {
						if (person_a_ap.peek().index == person_b_ap.peek().index) {
							answer = answer + person_a_ap.peek().volume / 2;
							answer = answer + person_b_ap.peek().volume / 2;
						} else {
							answer += person_a_ap.peek().volume;
							answer += person_b_ap.peek().volume;
						}
					} else if (person_a_ap.size() == 1) {
						if (person_a_ap.peek().index == person_b_ap.peek().index) {
							answer = answer + person_a_ap.peek().volume;
							person_b_ap.remove();
							answer = answer + person_b_ap.peek().volume;
						} else {
							answer += person_a_ap.peek().volume;
							answer += person_b_ap.peek().volume;
						}
					} else if (person_b_ap.size() == 1) {
						if (person_a_ap.peek().index == person_b_ap.peek().index) {
							person_a_ap.remove();
							answer = answer + person_a_ap.peek().volume;
							answer = answer + person_b_ap.peek().volume;
						} else {
							answer += person_a_ap.peek().volume;
							answer += person_b_ap.peek().volume;
						}
					} else {
						if (person_a_ap.peek().index == person_b_ap.peek().index) {
							AP tmp = person_a_ap.remove();
							int case1 = person_a_ap.peek().volume + person_b_ap.peek().volume;
							person_a_ap.add(tmp);

							person_b_ap.remove();
							int case2 = person_a_ap.peek().volume + person_b_ap.peek().volume;

							answer += Math.max(case1, case2);
						} else {
							answer = answer + person_a_ap.peek().volume;
							answer = answer + person_b_ap.peek().volume;
						}
					}
				}
//				System.out.println("t = " + t + " answer = " + answer);

				if (t == move_time)
					break;

				if (person_a_move[t] == 1) {
					person_a.y--;
				} else if (person_a_move[t] == 2) {
					person_a.x++;
				} else if (person_a_move[t] == 3) {
					person_a.y++;
				} else if (person_a_move[t] == 4) {
					person_a.x--;
				}

				if (person_b_move[t] == 1) {
					person_b.y--;
				} else if (person_b_move[t] == 2) {
					person_b.x++;
				} else if (person_b_move[t] == 3) {
					person_b.y++;
				} else if (person_b_move[t] == 4) {
					person_b.x--;
				}

				
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);

	}

}
