import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] numbers;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        numbers = new int[N];
        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(numbers);
        
        int minDiff = Integer.MAX_VALUE;
        
        int left = 0, right = 1;
        
        while(right < N) {
            int diff = numbers[right] - numbers[left];
            
            if(diff >= M) {
                minDiff = Math.min(minDiff, diff);
                left++;
            } else {
                right++;
            }
            
            if(left == right) {
                right++;
            }
        }
        
        System.out.println(minDiff);
    }
}