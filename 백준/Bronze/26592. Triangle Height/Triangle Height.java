import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());

        float a = Float.parseFloat(st.nextToken());
        float b = Float.parseFloat(st.nextToken());

        System.out.printf("The height of the triangle is %.2f units\n", 2*a/b);
        }
    }
}