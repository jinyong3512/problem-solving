class Solution {
    public int solution(String name) {                
        // 해당 위치에서 각각 목표 알파벳 횟수구하는 로직
        int[] alphaCounts = new int[name.length()];
        for (int i = 0; i < name.length(); i++) {
            int left = 'Z' - name.charAt(i) + 1;
            int right = name.charAt(i) - 'A';
            alphaCounts[i] = Math.min(left, right);
        }
                
        // 왼쪽으로 가면서 만들거나 오른쪽으로 가면서 만들거나 2가지 경우야. 가 아니래
        // 오른쪽으로가다가 A뭉텅이를 만났어. 건널지 vs 돌아갈지
        // 왼쪽으로가다가 A뭉텅이를 만났어. 건널지 vs 돌아갈지.
        int answer = name.length() - 1;
        
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                int left = i;
                int right = i;
                for (i++; i < name.length(); i++) {
                    if (name.charAt(i) == 'A') {
                        right = i;
                    } else {
                        break;
                    }
                }
                i--;
                
                // 0 1 2 3 4 5
                // left = 2) right = 3) 
                if (left > 0) {
                    // 오른쪽으로 갔다가 왼쪽 가기
                    answer = Math.min(answer, (left - 1) * 2 + name.length() - 1 - right);
                    // 왼쪽으로 갔다가 오른쪽 가기
                    answer = Math.min(answer, (name.length() - 1 - right) * 2 + left - 1);                
                } else {
                    answer = Math.min(answer, (name.length() - 1 - right));
                }
            }
        }
        
        // 총합 구하기
        for (int value: alphaCounts) {
            answer += value;
        }
        
        return answer;
    }
}