import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        int[][] num = new int[][]{
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5}, 
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        
        int[] scores = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < num.length; j++) {
                if (answers[i] == num[j][i % num[j].length])
                    scores[j]++;   
            }
        }
        
        for (int i = 0; i < scores.length; i++)
            System.out.printf("%d ", scores[i]);
        System.out.println();
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (arrayList.size() == 0)
                arrayList.add(i + 1);
            else {
                if (scores[arrayList.get(0) - 1] > scores[i]) {
                    continue;
                } else if (scores[arrayList.get(0) - 1] == scores[i]) {
                    arrayList.add(i + 1);
                } else {
                    arrayList = new ArrayList<>();
                    arrayList.add(i + 1);
                }
            }
        }
        
        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < answer.length; i++)
            answer[i] = arrayList.get(i);
        return answer;
    }
}