import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        // LinkedList 생성
        LinkedList<Character> list = new LinkedList<>();
        
        for(char c : s.toCharArray()) {
            list.add(c);
        }
        
        ListIterator<Character> cursor = list.listIterator(list.size());
        
        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("P")) {
                cursor.add(st.nextToken().charAt(0));
            }
            else if(cmd.equals("L")) {
                if(cursor.hasPrevious()) {
                    cursor.previous();
                }
            }
            else if(cmd.equals("D")) {
                if(cursor.hasNext()) {
                    cursor.next();
                }
            }

            else {
                if(cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            sb.append(c);
        }
        
        System.out.println(sb.toString());
    }
}