import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String s = br.readLine();
        int cnt = 0;

        boolean flag = false;
        boolean flag2 = false;
        for(int i = 0; i < n; i++){
            // 처음 A만남
            if(s.charAt(i) == 'A' && !flag) flag = true;
            // AN후에 A를 만남
            else if(s.charAt(i) == 'A' && flag && flag2) {
                cnt++;
                flag2 = false;
            }

            // A후에 N을 만남
            else if(s.charAt(i) == 'N' && flag && !flag2) flag2 = true;
            // N후에 N을 만남
            else if(s.charAt(i) == 'N' && flag && flag2){
                flag = false;
                flag2 = false;
            }
        }

        System.out.println(cnt);
    }
}