import java.io.*;
import java.util.*;

public class Main {

	public static int answer_max = Integer.MIN_VALUE;
	public static int answer_min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[] arr;
		int[] operator = new int[4];

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		///////////////////////////////////////////////////////

		// 순열
		ArrayList<Integer> arrayList = new ArrayList<>();
		recursion(arr, N, operator, 0, arrayList);

		System.out.println(answer_max);
		System.out.println(answer_min);

	}

	public static void recursion(int[] arr, int N, int[] operator, int depth, ArrayList<Integer> arrayList) {
		if (depth == N - 1) {
			int answer = arr[0];

			for (int i = 0; i < arrayList.size(); i++) {
				if(arrayList.get(i)==0) {
					answer = answer + arr[i+1];
				}
				else if (arrayList.get(i)==1) {
					answer = answer - arr[i+1];
				}
				else if (arrayList.get(i)==2) {
					answer = answer* arr[i+1];
				}
				else if (arrayList.get(i)==3) {
					answer  = answer / arr[i+1];
				}
			}
			
			answer_max = Math.max(answer_max,answer);
			answer_min = Math.min(answer_min,answer);
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				arrayList.add(i);
				recursion(arr, N, operator, depth + 1, arrayList);
				operator[i]++;
				arrayList.remove(arrayList.size() - 1);
			}
		}
	}
}