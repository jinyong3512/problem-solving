import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        
        int answer = 0;
        
        char[][] arr = new char[m][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            boolean stop = true;
            
            char[][] newArr = new char[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    newArr[i][j] = arr[i][j];
                }                
            }
            
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr[i].length - 1; j++) {
                    if (arr[i][j] != ' ' &&
                        arr[i][j] == arr[i][j + 1] &&
                        arr[i][j] == arr[i + 1][j] &&
                        arr[i][j] == arr[i + 1][j + 1]) {
                        stop = false;
                        newArr[i][j] = '@';
                        newArr[i][j + 1] = '@';
                        newArr[i + 1][j] = '@';
                        newArr[i + 1][j + 1] = '@';
                    }
                }
            }
            
            if (stop)
                break;
            
            for (int i = 0; i < newArr.length; i++) {
                for (int j = 0; j < newArr[0].length; j++) {
                    if (newArr[i][j] == '@') {
                        answer++;
                    }
                }
            }
            
            // System.out.println("newArr");
            // for (int i = 0; i < arr.length; i++) {
            //     for (int j = 0; j < arr[0].length; j++) {
            //         System.out.print(newArr[i][j]);
            //     }
            //     System.out.println();
            // }            
            
            arr = new char[newArr.length][newArr[0].length];
            
            for (int j = 0; j < newArr[0].length; j++) {
                Queue<Character> queue = new LinkedList<>();
                for (int i = newArr.length - 1; i >= 0; i--) {
                    if (newArr[i][j] != '@' && newArr[i][j] != ' ') {
                        queue.add(newArr[i][j]);
                    }
                }
                
                for (int i = arr.length - 1; i >= 0; i--) {
                    if (!queue.isEmpty()) {
                        arr[i][j] = queue.remove();
                    } else {
                        arr[i][j] = ' ';
                    }
                }
            }

            // System.out.println("arr");
            // for (int i = 0; i < arr.length; i++) {
            //     for (int j = 0; j < arr[0].length; j++) {
            //         System.out.print(arr[i][j]);
            //     }
            //     System.out.println();
            // }
            
        }
        
        return answer;
    }
}