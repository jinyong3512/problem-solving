class Solution {
    
    public static int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {   
        int[] qIndexs = new int [q.length];
        dfs(n, q, ans, 1, qIndexs, ans, 0);
        return answer;
    }
    
    public void dfs(int n, int[][] q, int[] ans, int curN, int[] curQIndexs, int[] curAns, int curDepth) {
        if (curDepth == 5) {
            for (int curAn: curAns){
                if (curAn != 0)
                    return;
            }
            answer++;
            return;
        }
        
        if (curN > n)
            return;
        
        // nTmp를 정답으로 만들 경우
        int[] newQIndexs = new int[curAns.length];
        for (int i = 0; i < newQIndexs.length; i++)
            newQIndexs[i] = curQIndexs[i];
        
        int[] newAns = new int[curAns.length];
        for (int i = 0; i < newAns.length; i++)
            newAns[i] = curAns[i];
        
        for (int i = 0; i < newAns.length; i++) {
            if (newQIndexs[i] == 5)
                continue;
            
            if (curN < q[i][newQIndexs[i]]) {
                
            } else if (curN == q[i][newQIndexs[i]]) {
                newAns[i]--;
                newQIndexs[i]++;
            } else {
                newQIndexs[i]++;
                i--;
            }                
        }
        dfs(n, q, ans, curN + 1, newQIndexs, newAns, curDepth + 1);
        
        // nTmp를 정답으로 만들지 않을 경우        
        dfs(n, q, ans, curN + 1, curQIndexs, curAns, curDepth);
    }
}