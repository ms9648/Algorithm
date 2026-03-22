import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int SOURCE, SINK, SIZE;
    static int[][] capacity;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SOURCE = 0;
        SINK = N+M+1;
        SIZE = N+M+2;

        capacity = new int[SIZE][SIZE];

        st = new StringTokenizer(br.readLine());
        for(int i = M+1; i <= N+M; i++){
            capacity[SOURCE][i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++){
            capacity[i][SINK] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = M+1; j <= N+M; j++){
                capacity[j][i] = Integer.parseInt(st.nextToken());
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