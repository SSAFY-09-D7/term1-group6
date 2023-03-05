import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    public static int N, M;
    public static char[][] arr;
    public static boolean[][] visited;
    public static int MINCNT = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node>{
        int i;
        int j;
        int cnt;    // 벽 뚫은 횟수

        Node(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[N][M];
        func();

        System.out.println(MINCNT);
    }

    public static void func(){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int ii = node.i;
            int jj = node.j;
            int cntcnt = node.cnt;

            if(ii == N-1 && jj == M-1){
                if(cntcnt < MINCNT)
                    MINCNT = cntcnt;
                return;
            }

            if(cntcnt > MINCNT)
                continue;

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii<0 || iii>=N || jjj<0 || jjj>=M)
                    continue;

                if(arr[iii][jjj] == '1' && !visited[iii][jjj] ){
                    visited[iii][jjj] = true;
                    queue.add(new Node(iii, jjj, cntcnt+1));
                }
                if(arr[iii][jjj] == '0' && !visited[iii][jjj]){
                    visited[iii][jjj] = true;
                    queue.add(new Node(iii, jjj, cntcnt));
                }

            }
        }
    }
}