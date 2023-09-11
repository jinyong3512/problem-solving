import java.io.*;
import java.util.*;

class Data {
	String word;
	int init_index;

	Data(String word, int init_index) {
		this.word = word;
		this.init_index = init_index;
	}
}

class Data2 {
	String word;
	int index1;
	int index2;

	Data2(String word, int index1, int index2) {
		this.word = word;
		this.index1 = index1;
		this.index2 = index2;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		String[] words;

		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for (int i = 0; i < N; i++)
			words[i] = br.readLine();

		////////////////////////////////////////////

		Data[] datas = new Data[N];
		for (int i = 0; i < N; i++) {
			datas[i] = new Data(words[i], i);
		}

		Arrays.sort(datas, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return o1.word.compareTo(o2.word);
			}
		});

		ArrayList<Data2> arrayList = new ArrayList<>();
		arrayList.add(new Data2("", Integer.MAX_VALUE, Integer.MAX_VALUE));

		for (int i = 0; i < N - 1; i++) {
			String word1 = datas[i].word;
			String word2 = datas[i + 1].word;

			String tmp_head = "";
			for (int word_index = 0; word_index < Math.min(word1.length(), word2.length()); word_index++) {
				if (word1.charAt(word_index) == word2.charAt(word_index)) {
					tmp_head += word1.charAt(word_index);
				} else {
					break;
				}
			}

			if (arrayList.get(0).word.length() > tmp_head.length()) {

			} else if (arrayList.get(0).word.length() == tmp_head.length()) {
				arrayList.add(new Data2(tmp_head, datas[i].init_index, datas[i + 1].init_index));
			} else {
				arrayList.clear();
				arrayList.add(new Data2(tmp_head, datas[i].init_index, datas[i + 1].init_index));
			}
		}

		int S = Integer.MAX_VALUE;
		int arrayList_S_index = -1;
		for (int i = 0; i < arrayList.size(); i++) {
			if (S > arrayList.get(i).index1) {
				S = arrayList.get(i).index1;
				arrayList_S_index = i;
			}
			if (S > arrayList.get(i).index2) {
				S = arrayList.get(i).index2;
				arrayList_S_index = i;
			}
		}

		int T = Integer.MAX_VALUE;
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).word.equals(arrayList.get(arrayList_S_index).word)) {
				if (S != arrayList.get(i).index1)
					T = Math.min(T, arrayList.get(i).index1);
				if (S != arrayList.get(i).index2)
					T = Math.min(T, arrayList.get(i).index2);
			}
		}

		System.out.println(words[S]);
		System.out.println(words[T]);
	}
}