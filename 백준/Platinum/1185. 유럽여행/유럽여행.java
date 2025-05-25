import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class Main {
    static int N, P;
    static int[] cost;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        Queue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int weight = 2 * c + cost[a] + cost[b];
            pq.offer(new Edge(a, b, weight));
        }

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < N - 1) {
            Edge e = pq.poll();

            if (union(e.from, e.to)) {
                totalCost += e.weight;
                edgesUsed++;
            }
        }

        int minCost = Integer.MAX_VALUE;
        
        for(int i = 1; i <= N; i++){
            minCost = Math.min(minCost, cost[i]);
        }
        
        System.out.println(totalCost+minCost);
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parent[rootB] = rootA;
        return true;
    }
}
