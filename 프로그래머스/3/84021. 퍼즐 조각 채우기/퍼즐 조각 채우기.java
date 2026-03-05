import java.util.*;

class Solution {
    
    private static final int[] DY = new int[]{-1, 1, 0, 0};
    private static final int[] DX = new int[]{0, 0, -1, 1};
    
    public int solution(int[][] game_board, int[][] table) {
        
        List<int[][]> gameBoardPeices = calculatePeices(game_board, 0);
        List<int[][]> tablePeices = calculatePeices(table, 1);
        
        int answer = 0;
        for (int rotation = 0; rotation < 4; rotation++) {
            answer += sameDelete(gameBoardPeices, tablePeices);
            rotate(tablePeices);
        }
        
        return answer;
    }
    
    private List<int[][]> calculatePeices(int[][] board, int value) {
        List<int[][]> peices = new ArrayList<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == value) {
                    int[][] peice = calculatePeice(board, value, i, j);
                    peices.add(peice);
                }
            }
        }
        
        return peices;
    }
    
    private int[][] calculatePeice(int[][] board, int value, int y, int x) {
        List<Point> points = bfs(board, value, y, x);
        
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Point point: points) {
            minY = Math.min(minY, point.getY());
            maxY = Math.max(maxY, point.getY());
            minX = Math.min(minX, point.getX());
            maxX = Math.max(maxX, point.getX());
        }
        
        int[][] peice = new int[maxY - minY + 1][maxX - minX + 1];
        for (Point point: points) {
            peice[point.getY() - minY][point.getX() - minX] = 1;
        }
        
        return peice;
    }
    
    private List<Point> bfs(int[][] board, int value, int y, int x) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(y, x));
        
        if (value == 0) {
            board[y][x] = 1;
        }
        else if (value == 1) {
            board[y][x] = 0;
        }
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(y, x));
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.getY() + DY[direction];
                int newX = curPoint.getX() + DX[direction];
                
                if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length) {
                    continue;
                }
                if (board[newY][newX] != value) {
                    continue; 
                }
                
                if (value == 0) {
                    board[newY][newX] = 1;
                }
                else if (value == 1) {
                    board[newY][newX] = 0;
                }
                points.add(new Point(newY, newX));
                queue.add(new Point(newY, newX));
            }
        }
        
        return points;
    }
    
    private int sameDelete(List<int[][]> gameBoardPeices, List<int[][]> tablePeices) {
        int tmpAnswer = 0;
        
        for (int i = 0; i < gameBoardPeices.size(); i++) {
            for (int j = 0; j < tablePeices.size(); j++) {
                if (same(gameBoardPeices.get(i), tablePeices.get(j))) {
                    for (int y = 0; y < gameBoardPeices.get(i).length; y++) {
                        for (int x = 0; x < gameBoardPeices.get(i)[0].length; x++) {
                            if (gameBoardPeices.get(i)[y][x] == 1)
                                tmpAnswer++;
                        }
                    }
                    
                    // for (int y = 0; y < gameBoardPeices.get(i).length; y++) {
                    //     for (int x = 0; x < gameBoardPeices.get(i)[0].length; x++) {
                    //         System.out.print(gameBoardPeices.get(i)[y][x]);
                    //     }
                    //     System.out.println();
                    // }
                    // System.out.println();
                    
                    gameBoardPeices.remove(i);
                    tablePeices.remove(j);
                    i--;
                    break;
                }
            }
        }
        
        return tmpAnswer;
    }
    
    private boolean same(int[][] piece1, int[][] piece2) {
        if (piece1.length != piece2.length)
            return false;
        if (piece1[0].length != piece2[0].length)
            return false;
        
        for (int i = 0; i < piece1.length; i++) {
            for (int j = 0; j < piece1[0].length; j++) {
                if (piece1[i][j] != piece2[i][j])
                    return false;
            }
        }
        
        return true;
    }
    
    private void rotate(List<int[][]> pieces) {
        for (int i = 0; i < pieces.size(); i++) {
            int[][] curPiece = pieces.get(i);
            int[][] newPiece = new int[curPiece[0].length][curPiece.length];
            
            for (int y = 0; y < curPiece.length; y++) {
                for (int x = 0; x < curPiece[0].length; x++) {
                    newPiece[x][newPiece[0].length - 1 - y] = curPiece[y][x];
                }
            }
            pieces.set(i, newPiece);
        }
    }
    
    private static class Point {
        private final int y;
        private final int x;
        
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        int getY() {
            return y;
        }
        int getX() {
            return x;
        }
    }
}