import java.util.*;
import java.math.*;

class Hand {
    int y,x;
    Hand (int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        ArrayList<Character> arrayList = new ArrayList<>();
        
        Hand leftHand = new Hand(3, 0);
        Hand rightHand = new Hand(3, 2);
        
        for (int i = 0; i < numbers.length; i++) {
            int curNumber = numbers[i];
            
            if (curNumber == 1) {
                arrayList.add('L');
                leftHand.y = 0;
                leftHand.x = 0;
            } else if (curNumber == 4) {
                arrayList.add('L');
                leftHand.y = 1;
                leftHand.x = 0;
            } else if (curNumber == 7) {
                arrayList.add('L');
                leftHand.y = 2;
                leftHand.x = 0;
            } else if (curNumber == 3) {
                arrayList.add('R');
                rightHand.y = 0;
                rightHand.x = 2;
            } else if (curNumber == 6) {
                arrayList.add('R');
                rightHand.y = 1;
                rightHand.x = 2;
            } else if (curNumber == 9) {
                arrayList.add('R');
                rightHand.y = 2;
                rightHand.x = 2;
            } else {
                int y, x;
                if (curNumber == 2) {
                    y = 0;
                    x = 1;
                } else if (curNumber == 5) {
                    y = 1;
                    x = 1;
                } else if (curNumber == 8) {
                    y = 2;
                    x = 1;
                } else {
                    y = 3;
                    x = 1;
                }
                
                int leftDistance = Math.abs(leftHand.y - y) + Math.abs(leftHand.x - x);
                int rightDistance = Math.abs(rightHand.y - y) + Math.abs(rightHand.x - x);
                
                if (leftDistance > rightDistance) {
                    arrayList.add('R');
                    rightHand.y = y;
                    rightHand.x = x;
                } else if (leftDistance == rightDistance) {
                    if (hand.equals("left")) {
                        arrayList.add('L');
                        leftHand.y = y;
                        leftHand.x = x;
                    } else {
                        arrayList.add('R');
                        rightHand.y = y;
                        rightHand.x = x;    
                    }
                } else {
                    arrayList.add('L');
                    leftHand.y = y;
                    leftHand.x = x;
                }
            }            
        }
        
        for (int i = 0; i < arrayList.size(); i++) {
            answer += arrayList.get(i);
        }
        
        return answer;
    }
}