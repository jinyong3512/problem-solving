import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        // Map uid, name
        // Queue enter or leave, uid
        
        Map<String, String> uidNames = new HashMap<>();
        Queue<Data> datas = new LinkedList<>();
        
        for (String curRecord: record) {
            StringTokenizer st = new StringTokenizer(curRecord);
            String action = st.nextToken();
            String uid = st.nextToken();
            
            if (action.equals("Enter")) {
                String name = st.nextToken();
                uidNames.put(uid, name);
                datas.add(new Data(action, uid));
            } else if (action.equals("Leave")) {
                datas.add(new Data(action, uid));
            } else if (action.equals("Change")) {
                String name = st.nextToken();
                uidNames.put(uid, name);                
            }
        }
        
        List<String> answers = new ArrayList<>();
        while (!datas.isEmpty()) {
            Data curData = datas.remove();
            
            StringBuilder sb = new StringBuilder();
            
            // name
            sb.append(uidNames.get(curData.uid));
            
            // action
            if(curData.action.equals("Enter")) {
                sb.append("님이 들어왔습니다.");
            } else if(curData.action.equals("Leave")) {
                sb.append("님이 나갔습니다.");
            }
            
            answers.add(sb.toString());
        }
        
        String[] answer = new String[answers.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answers.get(i);
        }
        
        return answer;
    }
    
    class Data{
        String action;
        String uid;
        Data(String action, String uid) {
            this.action = action;
            this.uid = uid;
        }
    }
}