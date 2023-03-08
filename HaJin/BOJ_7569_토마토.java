import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int M, N, H;
    public static int[][][] arr;
    public static boolean[][][] visited;
    public static int ANS = 0;
    public static int[] nh = {1, -1, 0, 0, 0, 0};
    public static int[] nr = {0, 0, -1, 1, 0, 0};
    public static int[] nc = {0, 0, 0, 0, -1, 1};
    public static Queue<Node> queue = new LinkedList<>();

    public static class Node{
        int i;
        int j;
        int k;
        int cnt;

        Node(int i, int j, int k, int cnt){
            this.i = i;
            this.j = j;
            this.k = k;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][N][M];
        visited = new boolean[H][N][M];

        for(int i=0; i<H; i++){
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<M; k++){
                    arr[i][j][k] = Integer.parseInt(st.nextToken());

                    if(arr[i][j][k] == 1){
                        queue.add(new Node(i, j, k, 0));
                        visited[i][j][k] = true;
                    }

                }
            }
        }

        bfs();

        boolean flag = false;

        L : for(int i=0; i<H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(arr[i][j][k] == 0){
                        flag = true;
                        break L;
                    }
                }
            }
        }

        if(flag)
            System.out.println("-1");
        else
            System.out.println(ANS);
    }


    public static void bfs(){

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int ii = node.i;
            int jj = node.j;
            int kk = node.k;
            int cntcnt = node.cnt;

            if(cntcnt > ANS)
                ANS = cntcnt;

            for(int p=0; p<6; p++){
                int iii = ii + nh[p];
                int jjj = jj + nr[p];
                int kkk = kk + nc[p];

                if(iii>=0 && iii<H && jjj>=0 && jjj<N && kkk>=0 && kkk<M && !visited[iii][jjj][kkk] && arr[iii][jjj][kkk]==0){
                    visited[iii][jjj][kkk] = true;
                    queue.add(new Node(iii, jjj, kkk, cntcnt+1));
                    arr[iii][jjj][kkk] = 1;
                }
            }
        }
    }

}