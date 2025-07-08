import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
        }

        System.out.println(dijkstra(1, N));
    }

    static int dijkstra(int start, int end){
        int[] distance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int dist = now[1];

            if (visited[node]) continue;
            visited[node] = true;

            for (int[] neighbor : graph.get(node)) {
                int next = neighbor[0];
                int cost = neighbor[1];

                if (distance[next] > dist + cost) {
                    distance[next] = dist + cost;
                    pq.offer(new int[]{next, distance[next]});
                }
            }
        }

        return distance[end];
    }
}
