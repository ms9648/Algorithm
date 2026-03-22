import java.util.*;
import java.io.*;

class Main {
    static int N, K, X;
    // 1 <= N, K, X <= 15
    // 1 <= K * X <= 15
    static HashSet<Integer>[] C;
    static Score[] B; // 각 학생의 점수
    static int S, T, SIZE;
    static int[][] capacity;
    static int[] parent;

    static class Score implements Comparable<Score> {
        int student; // 학생의 번호
        int score;

        public Score(int student, int score) {
            this.student = student;
            this.score = score;
        }

        @Override
        public int compareTo(Score o) {
            // 큰 값이 더 앞에 있어야 한다.
            return o.score - score;
        }

        @Override
        public String toString() {
            return "Score [student=" + student + ", score=" + score + "]";
        }


    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        S = 0;
        // 스터디: 1 ~ K
        // 학생: K+1 ~ K+N
        T = K+N+1;
        SIZE = K + N + 2;
        capacity = new int[SIZE][SIZE];

        C = new HashSet[SIZE];



        for(int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            C[i + K + 1] = new HashSet<>();

            int numberOfStudyToParticipate = Integer.parseInt(st.nextToken()); // 참가할 수 있는 스터디 수

            for(int j = 0; j < numberOfStudyToParticipate; j++) {
                // i번째 지원자가 넣을 수 있는 스터디
                C[i + K + 1].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 학생 점수 받기
        B = new Score[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = new Score(K+1+i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(B);

        // 학생 -> T
        for(int j = 1; j <= K; j++) {
            capacity[j][T] = X; // 각 스터디는 X명까지 수용한다.
        }

        for(int i = 0; i < N; i++) {
            // 점수 높은 학생부터 스터디로의 경로를 배정한다.
            // S -> B[i] -> STUDY

            Score s = B[i];
            capacity[S][s.student] = 1;

            // S -> 학생
            for(int study : C[s.student]) {
                capacity[s.student][study] = 1;

            }

            parent = new int[SIZE];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(S);
            parent[S] = S;

            // 경로 찾기
            while(!queue.isEmpty() && parent[T] == -1) {
                int curr = queue.poll();
                for(int next = 0; next < SIZE; next++) {
                    // 아직 방문하지 않았고 curr에서 갈 수 있는(아직 여유가 있는) 노드들에 대해서
                    if(parent[next] == -1 && capacity[curr][next] > 0) {
                        // curr -> next
                        parent[next] = curr;
                        queue.add(next);
                    }
                }
            }

            if(parent[T] == -1) {
                // 경로가 없습니다.
                // 이 학생은 스터디에 참가할 수 없음

                capacity[S][s.student] = 0;
                for(int study : C[s.student]) {
                    capacity[s.student][study] = 0;
                }

            }

            else {

                // 이미 사용된 경로랍니다
                for (int curr = T; curr != S; curr = parent[curr]) {
                    int next = parent[curr];
                    capacity[next][curr] -= 1;
                    capacity[curr][next] += 1;
                }
            }

        }

        int[] studyTotal = new int[K];

        for(int i = 1; i <= K; i++) {
            for(int j = K+1; j <= K+N; j++) {
                if(capacity[i][j] == 1) studyTotal[i-1]++;
            }
        }

        for(int i = 1; i <= K; i++) {
            System.out.print(studyTotal[i-1]);
            for(int j = K+1; j <= K+N; j++) {
                if(capacity[i][j] == 1) System.out.print(" "+(j-K));
            }
            System.out.println();
        }
//
//		System.out.println(max);
    }
}