import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stick = br.readLine();

        Queue<Character> queue = new ArrayDeque<>();
        // List<Integer> razer = new ArrayList<>();
        int[] razer2 = new int[1000001];
        
        int N = stick.length();
        boolean flag = false;

        int cnt = 0;
        int k = 0;
        
        for(int i = 0; i < N; i++){
            char s = stick.charAt(i);

            if(s == '('){
                queue.add(s);
                flag = true;
                // razer.add(0);
                k++;
            }

            if(s == ')' && flag){
                // 바로 직전이 '(' 이어서, 레이저 판정
                flag = false;
                // razer.remove(razer.size()-1);
                razer2[k-1] = 0;
                k--;
                
                // 레이저 쏘기
                queue.poll();

                if(!queue.isEmpty()){
                    // 만약 어떤 막대 위에서 쐈다면?
                    // 레이저 값 +1 씩
                    // for(int j = 0; j < razer.size(); j++){
                    //     razer.set(j, razer.get(j)+1);
                    // }
                    for(int j = 0; j < k; j++){
                        razer2[j] += 1;
                    }
                }
                
            }
            else if(s == ')' && !flag){
                // 레이저가 아닌, 막대기가 끝나는 경우
                // cnt += razer.get(razer.size()-1)+1;
                // razer.remove(razer.size()-1);
                cnt += razer2[k-1]+1;
                razer2[k-1] = 0;
                k--;
            }
        }

        System.out.println(cnt);        
    }
}