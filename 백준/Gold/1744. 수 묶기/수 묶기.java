import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////////////

		sort(arr);

		ArrayList<Integer> minus_numbers = new ArrayList<>();
		ArrayList<Integer> zero_numbers = new ArrayList<>();
		ArrayList<Integer> plus_numbers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (arr[i] < 0)
				minus_numbers.add(arr[i]);
			else if (arr[i] == 0)
				zero_numbers.add(arr[i]);
			else
				plus_numbers.add(arr[i]);
		}

		int answer = 0;

		while (true) {
			if (minus_numbers.size() >= 2) {
				answer = answer + minus_numbers.remove(0) * minus_numbers.remove(0);
			} else {
				break;
			}
		}

		if (minus_numbers.size() == 0) {

		} else if (minus_numbers.size() == 1) {
			// 0이 있어
			if (zero_numbers.size() != 0) {

			}
			// 0이 없어
			else {
				answer = answer + minus_numbers.remove(0);
			}
		} else {
			System.out.println("버그");
		}

		while (true) {
			if (plus_numbers.size() >= 2 && plus_numbers.get(plus_numbers.size() - 2) != 1) {
				answer = answer
						+ plus_numbers.remove(plus_numbers.size() - 1) * plus_numbers.remove(plus_numbers.size() - 1);
			} else {
				break;
			}
		}

		while (!plus_numbers.isEmpty()) {
			answer += plus_numbers.remove(0);
		}

		System.out.println(answer);

	}

	public static void sort(int[] arr) {

		// size
		int last_index = arr.length;

		// 1. 무작정 담기 O(N)
		int[] heap = new int[last_index + 1];
		for (int arr_index = 0; arr_index < arr.length; arr_index++)
			heap[arr_index + 1] = arr[arr_index];

		// 2. 맥스힙으로 바꾸기 O(N) * O(logN)
		buildHeap(heap, last_index);

		// 3. 힙 정렬 하기 O(N*logN)
		for (int i = last_index; i >= 1; i--) {
			int tmp = heap[1];
			heap[1] = heap[i];
			heap[i] = tmp;

			heapify(heap, 1, i - 1);
		}

		// 4. 반환하기 O(N)
		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap[i + 1];
		}
	}

	public static void heapify(int[] heap, int index, int last_index) {
		int max_index = index;
		int left_child = index * 2;
		int right_child = index * 2 + 1;

		if (left_child <= last_index && heap[left_child] > heap[max_index]) {
			max_index = left_child;
		}
		if (right_child <= last_index && heap[right_child] > heap[max_index]) {
			max_index = right_child;
		}

		if (max_index != index) {
			int tmp = heap[max_index];
			heap[max_index] = heap[index];
			heap[index] = tmp;
			heapify(heap, max_index, last_index);
		}

	}

	public static void buildHeap(int[] heap, int last_index) {
		for (int i = last_index / 2; i >= 1; i--) {
			heapify(heap, i, last_index);
		}
	}
}