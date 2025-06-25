import java.util.*;
import java.io.*;

class Main {
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            
            Queue<int[]> fireQueue = new ArrayDeque<>();
            Queue<int[]> personQueue = new ArrayDeque<>();
            
            for(int i = 0; i < h; i++){
                String s = br.readLine();
                for(int j = 0; j < w; j++){
                    map[i][j] = s.charAt(j);
                    if(map[i][j] == '@'){
                        personQueue.add(new int[]{i, j, 0});
                        map[i][j] = '.';
                    } else if(map[i][j] == '*'){
                        fireQueue.add(new int[]{i, j});
                    }
                }
            }
            
            int result = bfs(fireQueue, personQueue, w, h);
            if(result == -1){
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }
        }
    }
    
    static int bfs(Queue<int[]> fireQueue, Queue<int[]> personQueue, int w, int h){
        while(!personQueue.isEmpty()){

            int fireSize = fireQueue.size();
            for(int i = 0; i < fireSize; i++){
                int[] fire = fireQueue.poll();
                int fx = fire[0], fy = fire[1];
                
                for(int d = 0; d < 4; d++){
                    int nfx = fx + dx[d];
                    int nfy = fy + dy[d];
                    
                    if(nfx >= 0 && nfy >= 0 && nfx < h && nfy < w){
                        if(map[nfx][nfy] == '.'){
                            map[nfx][nfy] = '*';
                            fireQueue.add(new int[]{nfx, nfy});
                        }
                    }
                }
            }
            
            int personSize = personQueue.size();
            for(int i = 0; i < personSize; i++){
                int[] person = personQueue.poll();
                int px = person[0], py = person[1], time = person[2];
                
                for(int d = 0; d < 4; d++){
                    int npx = px + dx[d];
                    int npy = py + dy[d];
                    
                    if(npx < 0 || npy < 0 || npx >= h || npy >= w){
                        return time + 1;
                    }
                    
                    if(map[npx][npy] == '.'){
                        map[npx][npy] = '@';
                        personQueue.add(new int[]{npx, npy, time + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}