import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////

		sort(arr);

		int left = 0;
		int right = 1;
		int answer = Integer.MAX_VALUE;

		while (right < N) {
			int gap = arr[right] - arr[left];

			if (gap >= M) {
				answer = Math.min(gap, answer);
				left++;
				if (left == right)
					right++;
			} else
				right++;
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

//		for (int i = 1; i <= last_index / 2; i++)
//			heapify(heap, i, last_index);
	}
}