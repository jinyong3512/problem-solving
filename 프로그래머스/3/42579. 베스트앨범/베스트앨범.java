import java.util.*;

class Data{
    String genre;
    int play;
    Data (String genre, int play) {
        this.genre = genre;
        this.play = play;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            hashMap.put(genres[i], hashMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        ArrayList<Data> arrayList = new ArrayList<>();
        for (String key: hashMap.keySet()) {
            arrayList.add(new Data(key, hashMap.get(key)));
        }
        
        Collections.sort(arrayList, new Comparator<Data>(){
            @Override
            public int compare(Data a, Data b) {
                if (a.play > b.play) {
                    return -1;
                } else if (a.play == b.play) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        ArrayList<Integer> answerArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            String curGenre = arrayList.get(i).genre;
            
            int maxIndex = -1;
            int minIndex = -1;
            for (int j = 0; j < genres.length; j++) {
                if (!genres[j].equals(curGenre))
                    continue;
                
                if (maxIndex == -1) {
                    maxIndex = j;
                } else {
                    if (minIndex == -1) {
                        if (plays[maxIndex] >= plays[j]) {
                            minIndex = j;
                        } else {
                            minIndex = maxIndex;
                            maxIndex = j;
                        }
                    } else {
                        if (plays[j] > plays[maxIndex]) {
                            minIndex = maxIndex;
                            maxIndex = j;
                        } else if (plays[j] == plays[maxIndex]) {
                            if (plays[j] > plays[minIndex]) {
                                minIndex = j;
                            } 
                        } else {
                            if (plays[j] > plays[minIndex]) {
                                minIndex = j;
                            }
                        }
                    }
                }
            }
            
            answerArrayList.add(maxIndex);
            if (minIndex != -1) {
                answerArrayList.add(minIndex);
            }
            
        }
        
        int[] answer = new int[answerArrayList.size()];
        for (int i = 0; i < answerArrayList.size(); i++) {
            answer[i] = answerArrayList.get(i);
        }
        
        return answer;
    }
}