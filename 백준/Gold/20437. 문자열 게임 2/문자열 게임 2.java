import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // 전체 문자열에서 알파벳 개수 모두 세기
            int[] window = new int[26];
            for(int i = 0; i < W.length(); i++){
                window[W.charAt(i) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = 0;

            for(int i = 0; i < W.length(); i++){
                // 현재 알파벳이 문자열 내에 K개보다 적게 있는 경우는
                // 볼 필요 없다.
                if(window[W.charAt(i) - 'a'] < K) continue;

                // 현재 알파벳으로 시작하면서, K개를 포함하고, 현재
                // 알파벳으로 끝나는 단어 찾기
                String s = solve(W, W.charAt(i), i, K);

                if(s != null){
                    min = Math.min(min, s.length());
                    max = Math.max(max, s.length());
                }
            }

            if(min != Integer.MAX_VALUE){
                System.out.println(min+" "+max);
            }
            else{
                System.out.println(-1);
            }
            
        }
    }

    static String solve(String W, char alphabet, int start, int K){
        int cnt = 0;

        for(int i = start; i < W.length(); i++){
            if(W.charAt(i) == alphabet) cnt++;
            if(cnt == K) return W.substring(start, i+1);
        }

        return null;
    }
}