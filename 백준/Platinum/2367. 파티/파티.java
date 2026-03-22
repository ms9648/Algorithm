import java.util.*;
import java.io.*;

class Main {
    // N : 사람 수, (3 <= N <= 20)
    // K : 각 사람이 가져올 수 있는 최대 접시 수, (1 <= K <= 5)
    // D : 음식의 종류, (5 <= D <= 100)
    static int N, K, D;
    static int SOURCE, SINK, SIZE;
    static int[][] capacity;
    static int[] parent; // 역추적용

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // SOURCE = 0
        // 사람: D+1 ~ N+D
        // 음식: 1 ~ D
        // SINK = N+D+1;
        SIZE = N+D+2;
        SOURCE = 0;
        SINK = N+D+1;

        capacity = new int[SIZE][SIZE];

        st = new StringTokenizer(br.readLine());
        for(int d = 1; d <= D; d++){
            // 음식 -> SINK
            capacity[d][SINK] = Integer.parseInt(st.nextToken());
        }

        int tempN;
        int tempDish;
        for(int i = D+1; i <= N+D; i++){

            // 사람 -> 음식
            st = new StringTokenizer(br.readLine());

            tempN = Integer.parseInt(st.nextToken());

            // SOURCE -> 사람
            capacity[SOURCE][i] = tempN > K ? K : tempN;

            for(int j = 0; j < tempN; j++) {
                tempDish = Integer.parseInt(st.nextToken());
                capacity[i][tempDish] = 1;
            }
        }

        // BFS
        while(true){
            parent = new int[SIZE];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(SOURCE);
            parent[SOURCE] = SOURCE;

            while(!queue.isEmpty() && parent[SINK] == -1){
                // 방문하지 않은 노드에 대해서 그을 수 있는 경로 찾기
                int curr = queue.poll();
                // curr -> next로 그을 수 있다면 긋기(일단)
                for(int next = 0; next < SIZE; next++){
                    if(parent[next] == -1 && capacity[curr][next] > 0){
                        // 아직 용량이 된다면?
                        parent[next] = curr;
                        queue.add(next);
                    }
                }
            }

            if(parent[SINK] == -1){
                // 아직 경로를 못 찾았다? => 경로 없는 것
                break;
            }

            // 현재 경로에서 흐를 수 있는 최대의 유량은? => 어차피 1

            // SINK -> SOURCE까지 경로를 역추적
            for(int curr = SINK; curr != SOURCE; curr = parent[curr]){
                int next = parent[curr];

                capacity[next][curr] -= 1;
                capacity[curr][next] += 1;
            }
        }

        // 접시의 수 만큼
        int max = 0;
        for(int d = 1; d <= D; d++){
//            System.out.println("음식: "+d+", 개수: "+capacity[SINK][d]);
            max += capacity[SINK][d];
        }

        System.out.println(max);



    }
}