import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int SOURCE, SINK, TEMP, SIZE;
    static int[][] capacity;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        SOURCE = 0;
        SINK = N+M+1;
        TEMP = N+M+2;
        SIZE = N+M+3;

        capacity = new int[SIZE][SIZE];

        // SOURCE -> 직원
        for(int i = M+1; i <= N+M; i++){
            capacity[SOURCE][i] = 1;
        }

        // 일 -> SINK
        for(int i = 1; i <= M; i++){
            capacity[i][SINK] = 1;
        }

        // SOURCE → TEMP : K명이 누가 될지 모르니까 SOURCE -> TEMP -> 직원 -> 일 -> SINK에서 K개 길 찾기
        capacity[SOURCE][TEMP] = K;
        for(int i = M+1; i <= N+M; i++){
            capacity[TEMP][i] = 1;
        }



        for(int i = M+1; i <= N+M; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < cnt; j++){
                int job = Integer.parseInt(st.nextToken());
                capacity[i][job] = 1;
            }
        }

        while(true){
            parent = new int[SIZE];
            Arrays.fill(parent, -1);
            parent[SOURCE] = SOURCE;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(SOURCE);


            
            while(!queue.isEmpty() && parent[SINK] == -1){
                int curr = queue.poll();
                for(int next = 0; next < SIZE; next++){
                    if(parent[next] == -1 && capacity[curr][next] > 0){
                        queue.add(next);
                        parent[next] = curr;
                    }
                }
            }

            if(parent[SINK] == -1) break;

            int flow = Integer.MAX_VALUE;
            for(int curr = SINK; curr != SOURCE; curr = parent[curr]){
                int next = parent[curr];
                flow = Math.min(flow, capacity[next][curr]);
            }

            for(int curr = SINK; curr != SOURCE; curr = parent[curr]){
                int next = parent[curr];

                capacity[next][curr] -= flow;
                capacity[curr][next] += flow;
            }
        }

        int max = 0;
        for(int i = 1; i <= M; i++){
            max += capacity[SINK][i];
        }

        System.out.println(max);

    }
}