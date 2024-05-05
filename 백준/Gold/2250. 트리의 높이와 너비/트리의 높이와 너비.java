import java.io.*;
import java.util.*;

class Node {
    int value;
    Node leftChildNode;
    Node rightChildNode;

    Node(int value) {
        this.value = value;
        this.leftChildNode = null;
        this.rightChildNode = null;
    }
}

public class Main {

    public static Node root;
    public static Node findNode;
    public static HashMap<Integer, ArrayList<Integer>> countsPerDepth;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());

        int[][] datas = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            datas[i][0] = Integer.parseInt(st.nextToken());
            datas[i][1] = Integer.parseInt(st.nextToken());
            datas[i][2] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////////////////

        root = null;

        Arrays.sort(datas, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int rootNumber = 0;

        for (int i = 1; i <= N; i++) {

            boolean isRoot = true;

            for (int j = 1; j <= N; j++) {
                if (i == datas[j][1] || i == datas[j][2])
                    isRoot = false;
            }

            if (isRoot) {
                rootNumber = i;
            }
        }

//        System.out.println(rootNumber);

        root = new Node(rootNumber);

        if (datas[rootNumber][1] != -1) {
            root.leftChildNode = new Node(datas[rootNumber][1]);
            makeTree(root.leftChildNode, datas);
        }
        if (datas[rootNumber][2] != -1) {
            root.rightChildNode = new Node(datas[rootNumber][2]);
            makeTree(root.rightChildNode, datas);
        }

//        printInOrderTraversal(root);


        countsPerDepth = new HashMap<>();
        count = 0;

        solveInOrderTraversal(root, 1);

        int answerDepth = 0;
        int answerCount = 0;

        for (Integer depth : countsPerDepth.keySet()) {

            ArrayList<Integer> curArrayList = countsPerDepth.get(depth);

            int minValue = curArrayList.get(0);
            int maxValue = curArrayList.get(curArrayList.size() - 1);

            int curCount = maxValue - minValue + 1;

//            System.out.println("depth = " + depth + " curCount = " + curCount);

            if (answerCount < curCount) {
                answerDepth = depth;
                answerCount = curCount;
            }
        }

        System.out.println(answerDepth + " " + answerCount);



    }

    public static void makeTree(Node curNode, int[][] datas) {

        if (datas[curNode.value][1] != -1) {
            curNode.leftChildNode = new Node(datas[curNode.value][1]);
            makeTree(curNode.leftChildNode, datas);
        }
        if (datas[curNode.value][2] != -1) {
            curNode.rightChildNode = new Node(datas[curNode.value][2]);
            makeTree(curNode.rightChildNode, datas);
        }
    }

    public static void solveInOrderTraversal(Node curNode, int depth) {

        if (curNode.leftChildNode != null)
            solveInOrderTraversal(curNode.leftChildNode, depth + 1);

        count++;

        if (!countsPerDepth.containsKey(depth)) {
            countsPerDepth.put(depth, new ArrayList<>());
        }

        countsPerDepth.get(depth).add(count);


        if (curNode.rightChildNode != null)
            solveInOrderTraversal(curNode.rightChildNode, depth + 1);

    }

    public static void printInOrderTraversal(Node curNode) {
        if (curNode == null) return;

        printInOrderTraversal(curNode.leftChildNode);
        System.out.print(curNode.value + " ");
        printInOrderTraversal(curNode.rightChildNode);
    }
}
