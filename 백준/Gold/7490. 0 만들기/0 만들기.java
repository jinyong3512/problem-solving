import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        ArrayList<ArrayList<String>> bigArrayList = new ArrayList<>();
        for(int i =0  ; i <= 9 ; i++)
            bigArrayList.add(new ArrayList<>());
        
        bigArrayList.get(1).add("1");
        
        for(int i = 2 ; i<= 9 ; i++){
            for(int j = 0 ; j < bigArrayList.get(i-1).size() ; j++){
                String prevStr = bigArrayList.get(i-1).get(j);
                
                bigArrayList.get(i).add(prevStr+"+"+i);
                bigArrayList.get(i).add(prevStr+"-"+i);
                bigArrayList.get(i).add(prevStr+" "+i);                              
            }
        }
        
        ArrayList<ArrayList<String>> answerList = new ArrayList<>();
        for(int i =0  ; i <= 9 ; i++)
            answerList.add(new ArrayList<>());
        
        for(int i =3 ; i<= 9; i++){
            for(int j =0 ; j < bigArrayList.get(i).size(); j++){
                if(calculate(bigArrayList.get(i).get(j))==0)
                    answerList.get(i).add(bigArrayList.get(i).get(j));
            }
            
            Collections.sort(answerList.get(i));
        }
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T ; t++){
            int N = Integer.parseInt(br.readLine());
            
            for(int i =0 ; i < answerList.get(N).size(); i++){
                sb.append(answerList.get(N).get(i)).append("\n");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        
        
        
    }
    
    public static int calculate(String str){
        Deque<String> deque = new ArrayDeque<>();
        
        deque.addLast(str.substring(0,1));
        for(int i = 1 ; i < str.length() ; i +=2){
            if(str.charAt(i)=='+'){
                deque.addLast("+");
                deque.addLast(str.substring(i+1,i+2));
            }else if (str.charAt(i)=='-'){
                deque.addLast("-");              
                deque.addLast(str.substring(i+1,i+2));
            }else{
                deque.addLast(deque.removeLast()+str.substring(i+1,i+2));
            }           
        }
        
        int answer = Integer.parseInt(deque.removeFirst());
        while(!deque.isEmpty()){
            if(deque.peekFirst().equals("+")){
                deque.removeFirst();
                answer += Integer.parseInt(deque.removeFirst());
            }else{
                deque.removeFirst();
                answer -= Integer.parseInt(deque.removeFirst());
            }
        }
        
        return answer;
        
        
    }
}