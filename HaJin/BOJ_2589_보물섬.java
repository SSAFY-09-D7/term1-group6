import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int MAX = 0;

    public static class Node{
        int i;
        int j;
        int cnt;
        Node(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                if (c == 'W')
                    arr[i][j] = 0;
                else arr[i][j] = 1;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr[i][j] == 1){
                    visited = new boolean[N][M];
                    bfs(i, j);
                }
            }
        }

        System.out.println(MAX);

    }

    public static void bfs(int i, int j){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(i, j, 0));
        visited[i][j] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;
            int cntcnt = node.cnt;

            if(cntcnt > MAX){
                MAX = cntcnt;
            }

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];
                if(iii>=0 && iii<N && jjj>=0 && jjj<M && !visited[iii][jjj] && arr[iii][jjj]==1){
                    visited[iii][jjj] = true;
                    queue.add(new Node(iii, jjj, cntcnt+1));
                }
            }
        }
    }
}
