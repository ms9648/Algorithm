import java.io.*;
import java.util.*;

class Main {
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Set<Integer>[][] map;
	static boolean[][] room;
	static boolean[][] visited;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashSet[N+1][N+1];
		room = new boolean[N+1][N+1];
		visited = new boolean[N+1][N+1];
		cnt = 0;
		
		int x1, x2, y1, y2;
				
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			if(map[x1][y1] == null) map[x1][y1] = new HashSet<>();
			map[x1][y1].add(x2*1000 + y2);
		}
		
		bfs();
		
		System.out.println(cnt);
	}
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {1, 1});
		room[1][1] = true;
		visited[1][1] = true;
		cnt++;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			// 불을 킬 수 있는 곳이라면
			if(map[curr[0]][curr[1]] != null && map[curr[0]][curr[1]].size() != 0) {
				for(int info : map[curr[0]][curr[1]]) {
					int nx = info / 1000;
					int ny = info % 1000;
					
					if(room[nx][ny]) continue;
					room[nx][ny] = true;
					cnt++;
					
					for(int i = 0; i < 4; i++) {
						int nnx = nx + dx[i];
						int nny = ny + dy[i];
						
						if(nnx < 1 || nny < 1 || nnx > N || nny > N) continue;
						
						if(visited[nnx][nny]) {
							queue.add(new int[] {nnx, nny});
						}
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				
				if(nx < 1 || ny < 1 || nx > N || ny > N) continue;

				
				if(!room[nx][ny]) continue; // 불이 안 켜져 있으면 못 들어감
				
				if(!visited[nx][ny]) {
					// 방문하지 않은 곳이라면 방문 처리하고 들어감
					visited[nx][ny] = true;
					queue.add(new int[] {nx, ny});
				}
				
			}
		}
	}
}