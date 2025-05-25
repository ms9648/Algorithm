import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int from, to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {
    static int V, E;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Queue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.add(new Edge(A, B, C));
        }

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        long totalWeight = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < V - 1) {
            Edge e = pq.poll();

            if (union(e.from, e.to)) {
                totalWeight += e.weight;
                edgesUsed++;
            }
        }

        System.out.println(totalWeight);
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
