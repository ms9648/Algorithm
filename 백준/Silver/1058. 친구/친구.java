import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] adj = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            
            for(int j = 0; j < N; j++){
                if(s.charAt(j) == 'Y'){
                    adj[i][j] = true;
                }
                else{
                    adj[i][j] = false;
                }
            }
        }
        Set<Integer> set;
        int max = 0;
        for(int i = 0; i < N; i++){
            set = new HashSet<>();
            
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                // 직접 친구인 사람 넣기
                if(adj[i][j]) {
                    set.add(j);
                    for(int k = 0; k < N; k++){
                        if(i == k) continue;
                        if(adj[j][k]) set.add(k);
                    }
                }
            }

            max = Math.max(max, set.size());
        }
        System.out.println(max);
    }
}