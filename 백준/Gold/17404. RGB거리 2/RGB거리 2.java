import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] RGB;
    static int[][] dp;
    static final int INF = 1000001;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];
        dp = new int[N][3];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            RGB[i][0] = Integer.parseInt(st.nextToken()); // R
            RGB[i][1] = Integer.parseInt(st.nextToken()); // G  
            RGB[i][2] = Integer.parseInt(st.nextToken()); // B
        }
        
        int answer = INF;
        
        for(int firstColor = 0; firstColor < 3; firstColor++) {
            // DP 배열 초기화
            for(int i = 0; i < N; i++) {
                Arrays.fill(dp[i], INF);
            }
            
            dp[0][firstColor] = RGB[0][firstColor];
            
            for(int i = 1; i < N; i++) {
                for(int j = 0; j < 3; j++) {
                    for(int k = 0; k < 3; k++) {
                        if(j != k) { // 인접한 집은 같은 색이면 안됨
                            dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + RGB[i][j]);
                        }
                    }
                }
            }
            
            for(int lastColor = 0; lastColor < 3; lastColor++) {
                if(lastColor != firstColor) {
                    answer = Math.min(answer, dp[N-1][lastColor]);
                }
            }
        }
        
        System.out.println(answer);
    }
}