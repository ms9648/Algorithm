import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();
        String T = br.readLine();

        
        while(true) {
            if(T.length() < S.length()) {
                System.out.println(0);
                break;
            }
            if(T.equals(S)) {
                System.out.println(1);
                break;
            }
            
            if(T.length() == 0) {
                System.out.println(0);
                break;
            }
            
            if(T.charAt(T.length() - 1) == 'B') {
                T = T.substring(0, T.length() - 1);
                T = new StringBuilder(T).reverse().toString();
            } else {
                T = T.substring(0, T.length() - 1);
            }
        }
    }
}