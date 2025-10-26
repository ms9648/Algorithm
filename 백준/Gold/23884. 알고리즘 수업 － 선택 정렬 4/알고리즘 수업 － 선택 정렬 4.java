import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] A, B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];

        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            
            A[i] = temp;
            B[i] = temp;
        }

        Arrays.sort(B);
        
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < N; i++) position.put(A[i], i);

        int cnt = 0;
        for (int i = N - 1; i >= 1; i--) {
            int target = B[i];
            int j = position.get(target);

            if (j != i) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;

                position.put(target, i);
                position.put(temp, j);

                cnt++;
                if (cnt == K) {
                    print(A);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        
        for (int x : arr) sb.append(x).append(' ');
        
        System.out.println(sb.toString().trim());
    }
}
