import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {    
    static int maxNodeNumber, S, SINK;
    static int[][] capacity;
    static int[] parent;    
    static int N, M;
    static char[][] grid;
    static int[][] dirs = {{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}}; // A -> B를 보면 A와 B는 컨닝관계.. 즉 아래도 신경써야한다.
    
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
        int T = Integer.parseInt(br.readLine());
        
        while (T--> 0) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            grid = new char[N][M];
            
            for (int r = 0; r < N; r++) {
                String s = br.readLine();
                
                for (int c = 0; c < M; c++) {
                    grid[r][c] = s.charAt(c);
                }
            }
            
            
            

            S = 0;
            SINK = 1;            
            maxNodeNumber = 2 + N * M; // 최대 번호
            capacity = new int[maxNodeNumber][maxNodeNumber]; // 잔여 용량 그래프 capacity[S][흰색] = 1 => S->흰색 경로에서 1만큼 흘릴 수 있다.
            
            int tot = 0; // 앉을 수 있는 자리 수
            
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (grid[r][c] == 'x') continue;
                    
                    tot++;
                    int node = 2 + r * M + c;
                    
                    if (c % 2 == 0) {  // 흰색 칸 (짝수 열) (1, 1)이 검은색이면, (0, 0), (1, 0), (0, 2), (1, 2)가 검은 색 => 컬럼이 짝수면 흰색칸 && . x x . 인 경우 .끼리는 같은 색?
                        // S → 흰색 칸으로 연결
                        capacity[S][node] = 1;
                        
                        // 흰색 → 검은색                        
                        for (int[] d : dirs) {
                            int nr = r + d[0];
                            int nc = c + d[1];
                            
                            // 앉을 수 있는 칸이면
                            if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] != 'x') {
                                int neighbor = 2 + nr * M + nc; // 노드 번호 계산
                                
                                capacity[node][neighbor] = 1; // 흰색에서 검은색으로 간선 연결
                            }
                        }
                    } else {  // 검은색
                        // 검은색 → T 칸으로 연결
                        capacity[node][SINK] = 1;
                    }
                }
            }
            
            int max = 0; // 최대 유량
            
            while (true) {
                parent = new int[maxNodeNumber+1];
                Arrays.fill(parent, -1);
                parent[S] = S; // parent[0] = 0
                
                
                Queue<Integer> queue = new LinkedList<>(); // 입출력이 많으므로
                queue.add(S); // 시작점을 넣기
                
                
                
                while (!queue.isEmpty() && parent[SINK] == -1) {
                	// 경로를 먼저 찾거나 큐에 있는게 다 떨어질 때까지 반복
                	
                    int u = queue.poll();
                    
                    for (int v = 0; v < maxNodeNumber; v++) {
                        
                    	// 방문 안 했고 간선이 아직 있으면(처음에는 S -> 흰색 노드 다 넣음)
                        if (parent[v] == -1 && capacity[u][v] > 0) {
                        	// 연결
                            parent[v] = u;
                            queue.add(v);
                        }
                    }
                }
                
                // T까지 경로가 없으면 종료
                if (parent[SINK] == -1) break;
                
                // 최소 잔여 용량 찾기
                int flow = Integer.MAX_VALUE;
                // 역추적
                for (int v = SINK; v != S; v = parent[v]) {
                    int u = parent[v];
                    flow = Math.min(flow, capacity[u][v]); // 0 or 1 즉, 처음에는 다 1이니까 flow는 1 => 이 경로에서 흘릴 수 있는 최대 유량
                    
                    // 즉, S -> 흰 -> 검 -> ... -> T의 경로를 찾고, 그 경로의 최소 capacityacity를 찾기 => 그 경로에 흐를 수 있는 최대 유량
                }
                
                for (int v = SINK; v != S; v = parent[v]) {
                    int u = parent[v];
                    // 해당 경로의 역방향으로 이동하면서 흐를 수 있는 최대 유량만큰을 정뱡향 간선에는 빼고 역방향으로는 증가시킴
                    // 이렇게 하는 이유?
                    // 어떻게든 S -> 흰 -> 검 -> ... -> T로의 경로를 아주 많이 구함.
                    
                    capacity[u][v] -= flow;  // 정방향 잔여 용량 감소 => 확정하고 못 쓰게 만듦
                    capacity[v][u] += flow;  // 역방향 잔여 용량 증가 => 검->흰으로 경로가 생겼음!! 이제 이거로 갈 수도 있음
                }
                
                max += flow;
            }
            
            System.out.println(tot - max);
        }
    }
}