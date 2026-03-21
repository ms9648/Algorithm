import java.util.*;
import java.io.*;

public class Main {
	static int N, P, MAX;
	static Set[] capacity;
	static int S, T, maxNodeNumber;
	static int[] parent; // 400 * 400 = 160,000는 너무 많나보ㄷ..
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		S = 1;
		T = 2;
		
		maxNodeNumber = N + 1;
		MAX = 0;
		capacity = new HashSet[maxNodeNumber];
		
		for (int i = 0; i < maxNodeNumber; i++) {
		    capacity[i] = new HashSet<>();
		}
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			capacity[A].add(B); // 단방향
		}
		
		// 간선 돌면서 경로 찾기
		while(true) {
			parent = new int[maxNodeNumber];
			Arrays.fill(parent, -1);
			parent[S] = S;
			
			Queue<Integer> queue = new LinkedList<>(); // 입출력이 많으므로
			queue.add(S); // 시작점을 넣기
			
			while (!queue.isEmpty() && parent[T] == -1) {
				// 경로를 먼저 찾거나 큐에 있는게 다 떨어질 때까지 반복    	
	            int u = queue.poll();
	            
	            for (int v = 0; v < maxNodeNumber; v++) {
	            	// 방문 안 했고 간선이 아직 있으면(처음에는 S -> 노드 다 넣음)
	                if (parent[v] == -1 && capacity[u].contains(v)) {
	                	// 연결
	                    parent[v] = u;
	                    queue.add(v);
	                }
	            }
			}
        
        // T까지 경로가 없으면 종료
        if (parent[T] == -1) break;
        
        // 최소 잔여 용량 찾기
        int flow = Integer.MAX_VALUE;
        // 역추적
        for (int v = T; v != S; v = parent[v]) {
            int u = parent[v];
            flow = Math.min(flow, capacity[u].contains(v) ? 1 : 0); // 0 or 1 즉, 처음에는 다 1이니까 flow는 1 => 이 경로에서 흘릴 수 있는 최대 유량
            
            // 즉, S -> ... -> T의 경로를 찾고, 그 경로의 최소 capacityacity를 찾기 => 그 경로에 흐를 수 있는 최대 유량
        }
        
        for (int v = T; v != S; v = parent[v]) {
            int u = parent[v];
            // 해당 경로의 역방향으로 이동하면서 흐를 수 있는 최대 유량만큰을 정뱡향 간선에는 빼고 역방향으로는 증가시킴
            
            capacity[u].remove(v);  // 정방향 잔여 용량 감소 => 확정하고 못 쓰게 만듦
            capacity[v].add(u);  // 역방향 잔여 용량 증가 => 경로가 생겼음!! 이제 이거로 갈 수도 있음
        }
        
        	MAX += flow;
			
			
		}
		
		System.out.println(MAX);
		
		
		
		
	}
}