import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] matrix;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int[] explodeCnt;
    static int sharkX, sharkY;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharkX = N / 2 + 1;
        sharkY = N / 2 + 1;
        explodeCnt = new int[4];
        matrix = new int[N+1][N+1];
        
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < M; i++){
            blizzard();
        }
        
        int result = explodeCnt[1] + explodeCnt[2] * 2 + explodeCnt[3] * 3;
        System.out.println(result);
    }
    
    static void blizzard() throws Exception {
        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= s; i++){
            int nx = sharkX + dx[d] * i;
            int ny = sharkY + dy[d] * i;
            
            if(nx < 1 || ny < 1 || nx > N || ny > N) break;
            
            matrix[nx][ny] = 0;
        }
        
        while(true){
            // 한 칸씩 구슬을 이동시킴
            move();
            if(!explode()) break;
        }

        change();
    }
    
    static List<Integer> getList(){
        List<Integer> list = new ArrayList<>();
        
        int[] order = {3, 2, 4, 1}; // 좌, 하, 우, 상
        
        int x = sharkX;
        int y = sharkY;
        
        int step = 1;
        int dirIdx = 0;
        
        while(true){
            for(int repeat = 0; repeat < 2; repeat++){
                int dir = order[dirIdx % 4]; // 좌 하 우 상 순서로 바뀜

                for(int i = 0; i < step; i++){
                    x += dx[dir];
                    y += dy[dir];

                    // 끝까지 간 경우
                    if(x < 1 || y < 1 || x > N || y > N) return list;

                    // 0이 아닌 경우
                    if(matrix[x][y] != 0){
                        list.add(matrix[x][y]);
                    }
                }
                
                dirIdx++;
                
                // 끝을 만난 경우
                if(x < 1 || y < 1 || x > N || y > N) return list;
            }
            // 2번 꺾으면 횟수 증가
            step++;
        }
    }

    
    static void setList(List<Integer> list){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                matrix[i][j] = 0;
            }
        }
        
        int[] order = {3, 2, 4, 1};
        int x = sharkX;
        int y = sharkY;
        int step = 1;
        int dirIdx = 0;
        int listIdx = 0;
        
        outer: while(listIdx < list.size()){
            for(int repeat = 0; repeat < 2; repeat++){
                int dir = order[dirIdx % 4];
                for(int i = 0; i < step; i++){
                    x += dx[dir];
                    y += dy[dir];
                    if(x < 1 || y < 1 || x > N || y > N) break outer;
                    if(listIdx < list.size()){
                        matrix[x][y] = list.get(listIdx++);
                    }
                }
                dirIdx++;
            }
            step++;
        }
    }

    
    static void move(){
        List<Integer> list = getList(); // 구슬 리스트 반환
        setList(list);
    }

    
    static boolean explode(){
        List<Integer> list = getList();
        List<Integer> newList = new ArrayList<>();        
        
        boolean exploded = false;
        int i = 0;
        
        while(i < list.size()){
            int current = list.get(i);
            int count = 1;
            
            while(i + count < list.size() && list.get(i + count) == current){
                count++;
            }
            
            if(count >= 4){
                explodeCnt[current] += count;
                exploded = true;
            } else {
                for(int j = 0; j < count; j++){
                    newList.add(current);
                }
            }
            i += count;
        }
        
        setList(newList);
        return exploded;
    }
    

    static void change(){
        List<Integer> list = getList();
        List<Integer> newList = new ArrayList<>();
        
        int i = 0;
        while(i < list.size()){
            int current = list.get(i);
            int count = 1;
            
            while(i + count < list.size() && list.get(i + count) == current){
                count++;
            }
            
            newList.add(count);
            newList.add(current);
            i += count;
        }
        
        setList(newList);
    }
}