import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;

	}
}

public class Main { // 메인 클래스

	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N, M; // N*N 맵 , N조합M 할때 그 M이다.
		int[][] arr; // 0은 빈 칸, 1은 집, 2는 치킨집을 의미

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		////////////////////////////////////////////

		ArrayList<ArrayList<Integer>> big_distance_data = new ArrayList<>();
		ArrayList<Integer> small_distance_data;

		ArrayList<Point> home_arrayList = new ArrayList<>();
		ArrayList<Point> store_arrayList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1)
					home_arrayList.add(new Point(i, j));
				else if (arr[i][j] == 2)
					store_arrayList.add(new Point(i, j));
			}
		}

		for (int i = 0; i < home_arrayList.size(); i++) {
			Point home = home_arrayList.get(i);
			small_distance_data = new ArrayList<>();

			for (int j = 0; j < store_arrayList.size(); j++) {
				Point store = store_arrayList.get(j);
				// |r1-r2| + |c1-c2|
				int distance = Math.abs(home.y - store.y) + Math.abs(home.x - store.x);
				small_distance_data.add(distance);
			}

			big_distance_data.add(small_distance_data);
		}

		boolean[] visited = new boolean[store_arrayList.size()];
		recursion(big_distance_data, visited, 0, M, -1);
		
		System.out.println(answer);

	}

	public static void recursion(ArrayList<ArrayList<Integer>> big_distance_data, boolean[] visited, int depth, int r,
			int index) {
		if (depth == r) {

			int homes_distance = 0;
			for (int i = 0; i < big_distance_data.size(); i++) {

				int one_home_distance = Integer.MAX_VALUE;

				for (int j = 0; j < big_distance_data.get(i).size(); j++) {
					if (visited[j])
						one_home_distance = Math.min(one_home_distance, big_distance_data.get(i).get(j));
				}
				
				homes_distance += one_home_distance;
			}
			
			answer = Math.min(answer, homes_distance); 
			
			return;

		}

		for (int i = index + 1; i < big_distance_data.get(0).size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursion(big_distance_data, visited, depth + 1, r, i);
				visited[i] = false;
			}
		}
	}
}
