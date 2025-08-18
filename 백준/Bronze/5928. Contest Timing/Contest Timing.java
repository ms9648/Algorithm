import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        m = m - 11;

        if(m < 0){
            m = 60 + m;
            h--;
        }
        h = h - 11;
        if(h < 0){
            h = 24 + h;
            d--;
        }

        int t = 24*60*(d-11) + 60*h + m;
        if(t < 0) System.out.println(-1);
        else System.out.println(24*60*(d-11) + 60*h + m);
    }
}