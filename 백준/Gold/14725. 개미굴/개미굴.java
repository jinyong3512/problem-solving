import java.io.*;
import java.util.*;

class Node {
    String t;
    ArrayList<Node> children;

    Node(String t, ArrayList<Node> children) {
        this.t = t;
        this.children = children;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Node root = new Node(null, new ArrayList<>());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());

            String[] ts = new String[K];
            for (int j = 0; j < K; j++) {
                ts[j] = st.nextToken();
            }

            add(root, ts);

        }

        dfs(root, 0, sb);

        System.out.println(sb);
    }

    public static void add(Node root, String[] ts) {

        Node tmp = root;
        int i = 0;
        for (; i < ts.length; i++) {

            int j = 0;
            for (; j < tmp.children.size(); j++) {
                if (tmp.children.get(j).t.equals(ts[i])) {
                    break;
                }
            }

            // 못 찾음
            if (j == tmp.children.size()) {
                break;
            }
            // 찾음
            else {
                tmp = tmp.children.get(j);
            }
        }


        for (; i < ts.length; i++) {

            Node newNode = new Node(ts[i], new ArrayList<>());
            tmp.children.add(newNode);
            tmp = newNode;
        }
    }

    public static void dfs(Node curNode, int depth, StringBuilder sb) {

        if (depth != 0) {
            for (int i = 0; i < depth-1; i++) {
                sb.append("--");
            }
            sb.append(curNode.t);
            sb.append("\n");
        }

        Collections.sort(curNode.children, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.t.compareTo(o2.t);
            }
        });

        for (int i = 0; i < curNode.children.size(); i++) {
            dfs(curNode.children.get(i),depth+1,sb);
        }


    }
}
