import java.io.*;
import java.util.*;

class Main {
    static int R, C, cnt;
    static char[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        cnt = 0;
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            if (map[i][0] == '.') {
                visited[i][0] = true;

                if (dfs(i, 0)) {
                    cnt++;
                }


//                for (int i1 = 0; i1 < R; i1++) {
//                    for (int j = 0; j < C; j++) {
//                        System.out.print(visited[i1][j] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("====================");
            }
        }

        System.out.println(cnt);
    }

    static boolean dfs(int x, int y) {
        if (y == C - 1) {
            return true;
        }

        for (int j = 0; j < 3; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

            if (!visited[nx][ny] && map[nx][ny] == '.') {
                visited[nx][ny] = true;
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }
}