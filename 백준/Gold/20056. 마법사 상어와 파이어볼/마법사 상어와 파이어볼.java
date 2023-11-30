import java.io.*;
import java.util.*;

class Fire {
    int y, x, weight, speed, direction;

    Fire(int y, int x, int weight, int speed, int direction) {
        this.y = y;
        this.x = x;
        this.weight = weight;
        this.speed = speed;
        this.direction = direction;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, K;
        HashMap<Integer, ArrayList<Fire>> hashMap = new HashMap<>();

        /////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Fire fire = new Fire(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if (hashMap.containsKey(fire.y * N + fire.x)) {
                hashMap.get(fire.y * N + fire.x).add(fire);
            } else {
                ArrayList<Fire> newArrayList = new ArrayList<>();
                newArrayList.add(fire);
                hashMap.put(fire.y * N + fire.x, newArrayList);
            }
        }

//        System.out.println(hashMap.toString());

        ///////////////////////////////////////////////////////////////////////////////////////////////////

        for (int k = 0; k < K; k++) {

            // 이동하기

            HashMap<Integer, ArrayList<Fire>> newHashMap = new HashMap<>();

            for (Integer key : hashMap.keySet()) {
                ArrayList<Fire> fires = hashMap.get(key);

                for (Fire fire : fires) {
                    if (fire.direction == 0) {
                        fire.y -= fire.speed;
                    } else if (fire.direction == 1) {
                        fire.y -= fire.speed;
                        fire.x += fire.speed;
                    } else if (fire.direction == 2) {
                        fire.x += fire.speed;
                    } else if (fire.direction == 3) {
                        fire.y += fire.speed;
                        fire.x += fire.speed;
                    } else if (fire.direction == 4) {
                        fire.y += fire.speed;
                    } else if (fire.direction == 5) {
                        fire.y += fire.speed;
                        fire.x -= fire.speed;
                    } else if (fire.direction == 6) {
                        fire.x -= fire.speed;
                    } else {
                        fire.y -= fire.speed;
                        fire.x -= fire.speed;
                    }
                    if (fire.y >= N)
                        fire.y = fire.y % N;
                    if (fire.x >= N)
                        fire.x = fire.x % N;
                    if (fire.y < 0) {
//                    while (fire.y < 0)
//                        fire.y += N;
                        fire.y = (fire.y % N + N) % N;
                    }
                    if (fire.x < 0) {
//                    while (fire.x < 0)
//                        fire.x += N;
                        fire.x = (fire.x % N + N) % N;
                    }

                    if (newHashMap.containsKey(fire.y * N + fire.x)) {
                        newHashMap.get(fire.y * N + fire.x).add(fire);
                    } else {
                        ArrayList<Fire> tmp = new ArrayList<>();
                        tmp.add(fire);
                        newHashMap.put(fire.y * N + fire.x, tmp);
                    }
                }

            }

            hashMap = newHashMap;

            // 합치기
            for (Integer key : hashMap.keySet()) {
                int sumWeight = 0;
                int sumSpeed = 0;
                int firesCount = 0;
                int oddCount = 0;
                int evenCount = 0;

                ArrayList<Fire> tmp = hashMap.get(key);

                for(Fire fire: tmp){
                    sumWeight += fire.weight;
                    sumSpeed += fire.speed;
                    firesCount++;
                    if(fire.direction%2==1)
                        oddCount++;
                    else
                        evenCount++;
                }

                if(firesCount==1){

                }
                else{
                    tmp.clear();
                    if (sumWeight / 5 != 0) {

                        int speed = sumSpeed / firesCount;

                        // 틀림 1357
                        if (oddCount!=0 && evenCount!=0) {
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 1));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 3));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 5));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 7));
                        }
                        // 맞음 0246
                        else {
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 0));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 2));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 4));
                            tmp.add(new Fire(key/N, key%N, sumWeight / 5, speed, 6));

                        }
                    }
                }


            }

        }

        int answer =0 ;

        for(Integer key: hashMap.keySet()){
            for(Fire fire: hashMap.get(key)){
                answer += fire.weight;
            }
        }
        System.out.println(answer);


//
//            // 합치기
//            boolean[][] visited = new boolean[N][N];
////            boolean[] visited = new boolean[fires.size()];
//            ArrayList<Fire> new_fires = new ArrayList<>();
//
//            for (int i = 0; i < fires.size(); i++) {
//
//                if (visited[fires.get(i).y][fires.get(i).x])
//                    continue;
//
//                visited[fires.get(i).y][fires.get(i).x] = true;
//
////                if (visited[i])
////                    continue;
//
//                int sumWeight = 0;
//                int sumSpeed = 0;
//                int firesCount = 0;
//                ArrayList<Integer> directions = new ArrayList<>();
//
//                for (int j = i; j < fires.size(); j++) {
//                    if (fires.get(i).y == fires.get(j).y && fires.get(i).x == fires.get(j).x) {
//                        sumWeight += fires.get(j).weight;
//                        sumSpeed += fires.get(j).speed;
//                        firesCount++;
//                        directions.add(fires.get(j).direction);
////                        visited[j] = true;
//                    }
//                }
//
//                // 혼자야
//                if (firesCount == 1) {
//                    new_fires.add(fires.get(i));
//                }
//                // 친구 있어
//                else {
////                    // 최초 -1 모두 홀수면 1 모두 짝수면 0 틀려버리면 2
////                    int checkDirection = -1;
////
////                    for (Integer direction : directions) {
////                        if (checkDirection == -1) {
////                            checkDirection = direction % 2;
////                        } else {
////                            // 새 친구가 짝수
////                            if (direction % 2 == 0) {
////                                // 원래 것이 홀수
////                                if (checkDirection == 1) {
////                                    checkDirection = 2;
////                                    break;
////                                }
////                            }
////                            // 새 친구가 홀수
////                            else {
////                                // 원래 것이 짝수
////                                if (checkDirection == 0) {
////                                    checkDirection = 2;
////                                    break;
////                                }
////                            }
////                        }
//
//                    int checkDirection = -1;
//                    int oddCount = 0;
//                    int evenCount = 0;
//                    for (int direction : directions) {
//                        if (direction % 2 == 1) {
//                            oddCount++;
//                        } else if (direction % 2 == 0) {
//                            evenCount++;
//                        }
//                    }
//                    if (oddCount == 0 || evenCount == 0) {
//                        checkDirection = 1;
//                    } else {
//                        checkDirection = 2;
//                    }
//
//
//                    if (sumWeight / 5 != 0) {
//
//                        int speed = sumSpeed / firesCount;
//
//                        // 틀림 1357
//                        if (checkDirection == 2) {
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 1));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 3));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 5));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 7));
//                        }
//                        // 맞음 0246
//                        else {
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 0));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 2));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 4));
//                            new_fires.add(new Fire(fires.get(i).y, fires.get(i).x, sumWeight / 5, speed, 6));
//                        }
//                    }
//                }
//
//
//            }
//
//            fires = new_fires;
//
//        }
//
//
//        int answer = 0;
//        for (
//                Fire fire : fires) {
//            answer += fire.weight;
//        }
//        System.out.println(answer);

    }
}