import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////

        Arrays.sort(arr);

        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int lowerBoundIndex = lowerBound(arr, i, j);
                int upperBoundIndex = upperBound(arr, i, j);

                int tmpAnswer = upperBoundIndex - lowerBoundIndex;

//                System.out.println("value = " + (arr[i] + arr[j]) / 2 + " lowerBoundIndex = " + lowerBoundIndex + " upperBoundIndex = " + upperBoundIndex);

                if (lowerBoundIndex <= i && i < upperBoundIndex)
                    tmpAnswer--;
                if (lowerBoundIndex <= j && j < upperBoundIndex)
                    tmpAnswer--;

                if (tmpAnswer > 0)
                    hashMap.put(arr[i] + arr[j], true);
            }
        }

        int answer = 0;
        for (int value : arr) {
            if (hashMap.containsKey(value))
                answer++;
        }

        System.out.println(answer);

    }

    public static int lowerBound(int[] arr, int left, int right) {
        int value = arr[left] + arr[right];

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int curValue = arr[mid];

            if (curValue < value)
                low = mid + 1;
            else if (curValue == value)
                high = mid - 1;
            else
                high = mid - 1;
        }

        return low;
    }

    public static int upperBound(int[] arr, int left, int right) {
        int value = arr[left] + arr[right];

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int curValue = arr[mid];

            if (curValue < value)
                low = mid + 1;
            else if (curValue == value)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return low;
    }
}
