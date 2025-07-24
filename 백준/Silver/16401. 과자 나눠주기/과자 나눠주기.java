import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N;
    static int[] snacks;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        int start = 1;
        int end = 0;
        int answer = 0;
        
        st = new StringTokenizer(br.readLine());
        snacks = new int[N];
        
        for(int i = 0; i < N; i++){
            snacks[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, snacks[i]);
        }
        
        while(start <= end){
            int mid = (start + end) / 2;
            
            if(canDistribute(mid)){
                answer = mid; 
                start = mid + 1; 
            }
            else{
                end = mid - 1;
            }
        }
        
        System.out.println(answer);
    }
    
    static boolean canDistribute(int length) {
        if(length == 0) return true;
        
        int count = 0;
        for(int i = 0; i < N; i++){
            count += snacks[i] / length;
        }
        
        return count >= M;
    }
}