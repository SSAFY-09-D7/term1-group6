import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int R, C;
    public static char[][] arr;
    public static int start_x, start_y;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static boolean[][] jihoonVisited;
    public static int[][] fireCnt;
    public static int ANS = Integer.MAX_VALUE;

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
    public static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        jihoonVisited = new boolean[R][C];
        fireCnt = new int[R][C];

        //fireCnt -1로 초기화
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                fireCnt[i][j] = -1;
            }
        }

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                arr[i][j] = s.charAt(j);

                if(arr[i][j] == 'J'){
                    start_x = i;
                    start_y = j;

                    arr[i][j] = '.';
                }
                else if(arr[i][j] == 'F'){
                    fireCnt[i][j] = 0;
                    queue.add(new Node(i, j, 0));

                }
            }
        }

        fireBFS();
        jihoonBFS();

        if(ANS == Integer.MAX_VALUE){
            System.out.println("IMPOSSIBLE");
        }
        else{
            System.out.println(ANS+1);
        }
    }

    public static void fireBFS(){

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int ii = node.i;
            int jj = node.j;
            int cntcnt = node.cnt;

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<R && jjj>=0 && jjj<C && fireCnt[iii][jjj]==-1 && arr[iii][jjj]!='#'){
                    fireCnt[iii][jjj] = cntcnt + 1;
                    queue.add(new Node(iii, jjj, cntcnt+1));
                }
            }
        }
    }

    public static void jihoonBFS(){
        queue.add(new Node(start_x, start_y, 0));
        jihoonVisited[start_x][start_y] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int ii = node.i;
            int jj = node.j;
            int cntcnt = node.cnt;

            if(ii==0 || ii==R-1 || jj==0 || jj==C-1){
                ANS = cntcnt;

                return;
            }

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<R && jjj>=0 && jjj<C && !jihoonVisited[iii][jjj] && arr[iii][jjj]=='.'){
                    if((fireCnt[iii][jjj] == -1) || (cntcnt+1 < fireCnt[iii][jjj])){
                        jihoonVisited[iii][jjj] = true;
                        queue.add(new Node(iii, jjj, cntcnt+1));
                    }
                }
            }
        }
    }

}