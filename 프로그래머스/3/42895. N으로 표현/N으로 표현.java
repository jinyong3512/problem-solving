import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        List<List<Integer>> bigList = calculateBigList(N, number);
        return calculateAnswer(bigList, number);
    }
    
    private List<List<Integer>> calculateBigList(int N, int number) {
        // i번 썻을 때 만들 수 있는 수들
        List<List<Integer>> bigList = new ArrayList<>();
        bigList.add(new ArrayList<>());
        bigList.get(0).add(0);
        
        for (int i = 1; i <= 8; i++) {
            // 지금 만들고 싶은 애: i번 썻을 때 만들 수 있는 수들
            bigList.add(new ArrayList<>());
            bigList.get(i).add(bigList.get(i - 1).get(0) * 10 + N);
            
            for (int j = 1; j < i; j++) {
                // bigList.get(j), bigList.get(i - j)
                for (int k1 = 0; k1 < bigList.get(j).size(); k1++) {
                    for (int k2 = 0; k2 < bigList.get(i - j).size(); k2++) {
                        int number1 = bigList.get(j).get(k1);
                        int number2 = bigList.get(i - j).get(k2);
                        
                        bigList.get(i).add(number1 + number2);
                        bigList.get(i).add(number1 - number2);
                        bigList.get(i).add(number2 - number1);
                        bigList.get(i).add(number1 * number2);
                        if (number2 != 0)
                            bigList.get(i).add(number1 / number2);
                        if (number1 != 0)
                            bigList.get(i).add(number2 / number1);
                    }
                }
            }
        }
        
        return bigList;
    }
    
    private int calculateAnswer(List<List<Integer>> bigList, int number) {
        int answer = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < bigList.get(i).size(); j++) {
                if (bigList.get(i).get(j) == number)
                    return i;
            }
        }   
        return -1;
    }
}