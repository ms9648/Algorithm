import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < N; i++) {
            sb.append(br.readLine());
        }
        
        String initialMaze = sb.toString(); // 초기 미로 상태
        Queue<State> queue = new ArrayDeque<>(); // BFS 큐
        Map<String, int[][]> visited = new HashMap<>(); // 방문 처리(미로의 상태에 따른 방문 처리 상태)
        
        queue.add(new State(0, 0, 0, initialMaze));
        int[][] initialVisited = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(initialVisited[i], Integer.MAX_VALUE);
        }
        
        initialVisited[0][0] = 0;
        visited.put(initialMaze, initialVisited);
        
        while(!queue.isEmpty()) {
            State current = queue.poll();
            
            int x = current.x;
            int y = current.y;
            int time = current.time;
            String maze = current.maze;

            // 도착했는지?
            if(x == N-1 && y == M-1) {
                System.out.println(time);
                return;
            }
            
            int[][] currentVisited = visited.get(maze);
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                
                // 현재 방에서 해당 방향으로 문이 있는지 체크
                if(!hasDoor(maze.charAt(x*M+y), i)) continue;
                // 다음 방으로 갈 수 있는 지 체크
                if(!hasDoor(maze.charAt(nx*M+ny), getOppositeDir(i))) continue;
                
                if(currentVisited[nx][ny] > time + 1) {
                    currentVisited[nx][ny] = time + 1;
                    queue.add(new State(nx, ny, time + 1, maze));
                }
            }
            
            // 회전 시키기
            String rotatedMaze = rotateMaze(maze, x, y);
            
            // 처음 보는 미로의 상태인 경우
            if(!visited.containsKey(rotatedMaze)) {
                int[][] newVisited = new int[N][M];
                
                for(int i = 0; i < N; i++) {
                    Arrays.fill(newVisited[i], Integer.MAX_VALUE);
                }
                
                visited.put(rotatedMaze, newVisited);
            }
            
            int[][] rotatedVisited = visited.get(rotatedMaze);
            if(rotatedVisited[x][y] > time + 1) {
                rotatedVisited[x][y] = time + 1;
                queue.add(new State(x, y, time + 1, rotatedMaze));
            }
        }
        
        System.out.println(-1);
    }
    
    // 현재 방 타입과 방향에 따른 문의 여부 체크하기
    static boolean hasDoor(char room, int dir) {
        switch(room) {
            case 'A': return true; // 모든 방향에 문
            case 'B': return false; // 문 없음
            case 'C': return dir == 0 || dir == 1; // 상, 하만
            case 'D': return dir == 2 || dir == 3; // 좌, 우만
        }
        return false;
    }
    
    // 반대 방향 구하기
    // 00(上) ^ 01 = 01(下), 01(下) ^ 01 = 00(上), 10(左) ^ 01 = 11(右), 11(右) ^ 01 = 10(左)
    static int getOppositeDir(int dir) {
        return dir ^ 1;
    }
    
    // 미로 회전 (현재 위치의 행과 열 회전)
    static String rotateMaze(String maze, int curX, int curY) {
        char[] rotated = maze.toCharArray();
        
        // 현재 행의 모든 방 회전
        for(int i = 0; i < M; i++) {
            int pos = curX * M + i;
            rotated[pos] = rotateRoom(rotated[pos]);
        }
        
        // 현재 열의 모든 방 회전 (현재 위치는 이미 회전했으므로 한 번 더 회전)
        for(int i = 0; i < N; i++) {
            int pos = i * M + curY;
            rotated[pos] = rotateRoom(rotated[pos]);
        }
        
        return new String(rotated);
    }
    
    // 방 교환하기
    static char rotateRoom(char room) {
        switch(room) {
            case 'A': return 'A';
            case 'B': return 'B';
            case 'C': return 'D';
            case 'D': return 'C';
        }
        return room;
    }
    
    static class State {
        int x, y, time;
        String maze;
        
        State(int x, int y, int time, String maze) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.maze = maze;
        }
    }
}