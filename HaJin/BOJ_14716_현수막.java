import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] nr = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] nc = {-1, 0, 1, -1, 1, -1, 0, 1};
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

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[M][N];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        visited = new boolean[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 1 && !visited[i][j]){
                    cnt += 1;
                    visited[i][j] = true;
                    BFS(i, j);
                }
            }
        }
        System.out.println(cnt);
    }

    public static void BFS(int i, int j){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;

            for(int p=0; p<8; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<M && jjj>=0 && jjj<N && !visited[iii][jjj] && arr[iii][jjj]==1){
                    visited[iii][jjj] = true;
                    queue.add(new Node(iii, jjj));
                }
            }
        }
    }


}