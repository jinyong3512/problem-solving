import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
                               
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        for (int i = 0; i < terms.length; i++) {
            StringTokenizer st = new StringTokenizer(terms[i]);
            String key = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            hashMap.put(key, value);
        }
        
        int[][] calPrivacies = new int[privacies.length][3];
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            
            int plus = hashMap.get(str2);
            
            int year = Integer.parseInt(str1.substring(0, 4));
            int month = Integer.parseInt(str1.substring(5, 7));
            int day = Integer.parseInt(str1.substring(8,10));
            
            year += plus / 12;
            month += plus % 12;
            if (month >= 12) {
                year++;
                month -= 12;
            }
            
            day--;
            
            if (day == 0) {
                month -=1;
                day = 28;
            }
            
            if (month == 0) {
                year -= 1;
                month = 12;
            }
            
            calPrivacies[i][0] = year;
            calPrivacies[i][1] = month;
            calPrivacies[i][2] = day;
        }
        
        StringTokenizer st = new StringTokenizer(today);
        String str1 = st.nextToken();

        int year = Integer.parseInt(str1.substring(0, 4));
        int month = Integer.parseInt(str1.substring(5, 7));
        int day = Integer.parseInt(str1.substring(8,10));
        
        int[] todays = new int[3];
        todays[0] = year;
        todays[1] = month;
        todays[2] = day;
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        for (int i = 0; i < calPrivacies.length; i++) {
            if (todays[0] > calPrivacies[i][0]) {
                arrayList.add(i + 1);
            } else if (todays[0] == calPrivacies[i][0]) {
                
                if (todays[1] > calPrivacies[i][1]) {
                    arrayList.add(i + 1);
                } else if (todays[1] == calPrivacies[i][1]) {
                    
                    if (todays[2] > calPrivacies[i][2]) {
                        arrayList.add(i + 1);
                    }
                }
            }
        }
        
        for (int i = 0; i < calPrivacies.length; i++) {
            System.out.print(calPrivacies[i][0]);
            System.out.print(calPrivacies[i][1]);
            System.out.println(calPrivacies[i][2]);
        }
        
        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            answer[i] = arrayList.get(i);
        
        return answer;
    }
}