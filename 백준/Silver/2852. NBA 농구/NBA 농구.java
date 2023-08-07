import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		String[][] data;

		N = Integer.parseInt(br.readLine());
		data = new String[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = st.nextToken();
			data[i][1] = st.nextToken();
		}

		//////////////////////////////////////////////

		int team1_goal = 0;
		int team2_goal = 0;
		int team1_time = 0;
		int team2_time = 0;
		int last_time = 0;
		for (int i = 0; i < N; i++) {
			if (team1_goal > team2_goal) {
				team1_time = team1_time + (Integer.parseInt(data[i][1].substring(0, 2)) * 60
						+ Integer.parseInt(data[i][1].substring(3, 5))) - last_time;
			} else if (team1_goal < team2_goal) {
				team2_time = team2_time + (Integer.parseInt(data[i][1].substring(0, 2)) * 60
						+ Integer.parseInt(data[i][1].substring(3, 5))) - last_time;
			}

			last_time = Integer.parseInt(data[i][1].substring(0, 2)) * 60
					+ Integer.parseInt(data[i][1].substring(3, 5));

			if (data[i][0].equals("1")) {
				team1_goal++;
			} else {
				team2_goal++;
			}

		}

		if (team1_goal > team2_goal) {
			team1_time = team1_time + (48 * 60) - last_time;
		} else if (team1_goal < team2_goal) {
			team2_time = team2_time + (48 * 60) - last_time;
		}

		System.out.printf("%02d:%02d\n", team1_time / 60, team1_time % 60);
		System.out.printf("%02d:%02d\n", team2_time / 60, team2_time % 60);

	}

}
