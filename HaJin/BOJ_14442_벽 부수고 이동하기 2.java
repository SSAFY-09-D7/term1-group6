import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, K;
    public static char[][] arr;
    public static boolean[][][] visited;

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int ANS = -1;

    public static class Node{
        int i;
        int j;
        int breakCnt;
        int moveCnt;

        Node(int i, int j, int breakCnt, int moveCnt){
            this.i = i;
            this.j = j;
            this.breakCnt = breakCnt;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visited = new boolean[N][M][K+1];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        bfs();

        System.out.println(ANS);
    }

    public static void bfs() {

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0, 0, 1));
        for(int i=0; i<K+1; i++)
            visited[0][0][i] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;
            int nbc = node.breakCnt;
            int nmc = node.moveCnt;

            if(ANS != -1 && nmc > ANS)
                continue;

            if(ii == N-1 && jj == M-1) {
                if(ANS == -1)
                    ANS = nmc;

                else if(ANS > nmc)
                    ANS = nmc;
                return;
            }

            for(int p=0; p<4; p++) {
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<M) {
                    if(arr[iii][jjj] == '1' && nbc<K && !visited[iii][jjj][nbc+1]) {
                        queue.add(new Node(iii, jjj, nbc+1, nmc+1));
                        visited[iii][jjj][nbc+1] = true;
                    }
                    if(arr[iii][jjj] == '0' && !visited[iii][jjj][nbc]) {
                        queue.add(new Node(iii, jjj, nbc, nmc+1));
                        visited[iii][jjj][nbc] = true;
                    }
                }
            }
        }
    }

}