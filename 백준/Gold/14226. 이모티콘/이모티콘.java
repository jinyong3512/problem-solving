import java.io.*;
import java.util.*;

class Data {
    int number;
    int clip;
    int depth;

    Data(int number, int clip, int depth) {
        this.number = number;
        this.clip = clip;
        this.depth = depth;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int S = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[1001][1001];
        Queue<Data> queue = new LinkedList<>();

        visited[1][0] = true;
        queue.add(new Data(1, 0, 0));

        while (!queue.isEmpty()) {
            Data curData = queue.remove();

            if (curData.number == S) {
                System.out.println(curData.depth);
                break;
            }

            if (!visited[curData.number][curData.number]) {
                visited[curData.number][curData.number] = true;
                queue.add(new Data(curData.number, curData.number, curData.depth + 1));
            }

            if (curData.clip != 0) {
                if (curData.number + curData.clip < 1001) {
                    if (!visited[curData.number + curData.clip][curData.clip]) {
                        visited[curData.number + curData.clip][curData.clip] = true;
                        queue.add(new Data(curData.number + curData.clip, curData.clip, curData.depth + 1));
                    }
                }
            }

            if (curData.number - 1 >= 0) {
                if (!visited[curData.number - 1][curData.clip]) {
                    visited[curData.number - 1][curData.clip] = true;
                    queue.add(new Data(curData.number - 1, curData.clip, curData.depth + 1));
                }
            }

        }
    }
}