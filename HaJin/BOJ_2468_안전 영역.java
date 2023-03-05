import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N;
    public static int[][] arr;
    public static int[][] newArr;
    public static boolean[][] visited;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static int MAXCNT = 0;

    public static class Node{
        int i;
        int j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        int max = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(max < arr[i][j])
                    max = arr[i][j];
            }
        }

        // 0부터 max까지 비가 올 수 있음
        for(int k = 0; k<=max; k++) {

            newArr = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] <= k){
                        newArr[i][j] = 0;
                    }
                    else{
                        newArr[i][j] = arr[i][j];
                    }

                }
            }

            // bfs 돌리기
            int cnt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(newArr[i][j]!=0 && !visited[i][j]){
                        cnt += 1;
                        bfs(i, j);
                    }
                }
            }

            if(cnt > MAXCNT)
                MAXCNT = cnt;

        }

        System.out.println(MAXCNT);

    }

    public static void bfs(int i, int j){

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j));
        visited[i][j] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int ii = node.i;
            int jj = node.j;

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<N && !visited[iii][jjj] && newArr[iii][jjj]!=0){
                    queue.add(new Node(iii, jjj));
                    visited[iii][jjj] = true;
                }
            }
        }

    }

}