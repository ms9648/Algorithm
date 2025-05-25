import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E, cnt;
    static List<List<Integer>> graph;
    static int[] cutVertex;
    static int[] discSeq;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        cnt = 0;
        cutVertex = new int[V+1];
        discSeq = new int[V+1];
        
        for(int i = 0; i <= E; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        for(int i = 1; i <= V; i++){
            if(discSeq[i] == 0){
                seek(i, true);
            }
        }

        int cutVertexCnt = 0;

        for(int i = 1; i <= V; i++){
            if(cutVertex[i] == 1){
                cutVertexCnt++;
            }
        }
        System.out.println(cutVertexCnt);

        for(int i = 1; i <= V; i++){
            if(cutVertex[i] == 1){
                System.out.print(i+" ");
            }
        }

        
    }

    static int seek(int now, boolean root){
        // 발견한 번호 부여
        discSeq[now] = ++cnt;

        int num = cnt;
        int child = 0;
        
        // 현재 노드의 간선 수만큼 반복
        for(int i = 0; i < graph.get(now).size(); i++){
            int next = graph.get(now).get(i);
            // 만약 연결되어 있는 노드가 아직 방문되지 않은 뒷 노드라면?
            if(discSeq[next] == 0){
                child++;
                int temp = seek(next, false);
                num = Math.min(num, temp);
                // 루트가 아니면서 자식 노드가 갈 수 있는 노드의 번호가 여전히 더 작다면?
                if(!root && temp >= discSeq[now]){
                    // 해당 점은 단절점
                    cutVertex[now] = 1;
                }   
            }
            // 방문한 노드였다면
            else{
                // 연결되어 있는 노드 중에서 가장 빠른 곳
                num = Math.min(num, discSeq[next]);
            }
        }
        if(root && child > 1){
            cutVertex[now] = 1;
        }

        return num;
    }
}

// https://ip99202.github.io/posts/%EB%8B%A8%EC%A0%88%EC%A0%90-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/
// 위 블로그 보고 공부한 내용