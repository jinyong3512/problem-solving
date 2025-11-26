import java.util.*;

class Plan {
    String name;
    int start, playtime;
    
    Plan (String name, int start, int playtime) {
        this.name = name;
        this.start = start;
        this.playtime = playtime;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        
        Plan[] newPlans = new Plan[plans.length];
        for (int i = 0; i < plans.length; i++) {
            newPlans[i] = new Plan (
                plans[i][0],
                Integer.parseInt(plans[i][1].substring(0,2)) * 60 
                + Integer.parseInt(plans[i][1].substring(3, 5)),
                Integer.parseInt(plans[i][2])
            );
        }
        
        Arrays.sort(newPlans, new Comparator<Plan>(){
            @Override
            public int compare (Plan a, Plan b) {
                    return a.start-b.start;
            }
        });
        
        ArrayList<String> answer = new ArrayList<>();
        Stack<Plan> stack = new Stack<>();
        
        stack.push(newPlans[0]);
        
        for (int i = 1; i < newPlans.length; i++) {
            int gap = newPlans[i].start - newPlans[i-1].start;
            
            while (!stack.isEmpty()) {
                Plan curPlan = stack.pop();
                if (curPlan.playtime <= gap) {
                    answer.add(curPlan.name);
                    gap -= curPlan.playtime;
                    
                    if (gap <= 0)
                        break;
                } else {
                    curPlan.playtime -= gap;
                    stack.push(curPlan);
                    break;
                }
            }
            stack.push(newPlans[i]);
        }
        
        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }
        
        String[] realAnswer = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++){
            realAnswer[i] = answer.get(i);
        }
        
        return realAnswer;
    }
}