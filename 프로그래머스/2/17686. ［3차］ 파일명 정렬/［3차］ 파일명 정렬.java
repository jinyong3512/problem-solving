import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        List<Data> list = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
            
            int index = 0;
            
            // head
            for (; index < files[i].length(); index++) {
                if (files[i].charAt(index) >= '0' && files[i].charAt(index) <= '9') {
                    break;
                }
            }
            String head = files[i].substring(0, index);            
            
            // number
            StringBuilder sb = new StringBuilder();
            for (; index < files[i].length(); index++) {
                if (sb.length() >= 5) {
                    break;
                }
                if (files[i].charAt(index) >= '0' && files[i].charAt(index) <= '9') {
                    sb.append(files[i].charAt(index));
                } else {
                    break;
                }
            }
            String number = sb.toString();
            
            // tail
            String tail = files[i].substring(index);
            
            
            // test
            //System.out.printf("%s %s %s\n", head, number, tail);
            
            list.add(new Data(head, number, tail));
        }
        
        Collections.sort(list, new Comparator<Data>(){
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.head.toUpperCase().compareTo(o2.head.toUpperCase()) < 0) {
                    return -1;
                } else if (o1.head.toUpperCase().compareTo(o2.head.toUpperCase()) > 0) {
                    return 1;
                } else {
                    if (Integer.parseInt(o1.number) < Integer.parseInt(o2.number)) {
                        return -1;
                    } else if (Integer.parseInt(o1.number) > Integer.parseInt(o2.number)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
                        
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).head + list.get(i).number + list.get(i).tail;
        }
        
        return answer;
    }
    
    class Data {
        String head, number, tail;
        
        Data(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }            
    }
}