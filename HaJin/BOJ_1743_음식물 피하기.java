import java.io.*;
import java.util.*;

class Info{
    int idx;
    int height;

    Info(int idx, int height){
        this.idx = idx;
        this.height = height;
    }
}

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static int N, M, K;
    public static int[][] arr;
    public static boolean[][] visited;

    public static int[] a = {0, 0, -1, 1};
    public static int[] b = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a-1][b-1] = 1;
        }

        int max_size = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
               if(arr[i][j]==1 && !visited[i][j]){
                   visited[i][j] = true;
                   int size = bfs(i, j, 1);
                   if(size > max_size)
                       max_size = size;
               }
            }
        }

        System.out.println(max_size);
    }

    public static int bfs(int i, int j, int cnt){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(i, j));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;

            for(int p=0; p<4; p++){
                int xx = x + a[p];
                int yy = y + b[p];

                if(xx>=0 && yy>=0 && xx<N && yy<M && !visited[xx][yy] && arr[xx][yy] == 1){
                    queue.add(new Node(xx, yy));
                    visited[xx][yy] = true;
                    cnt+=1;
                }
            }
        }
        return cnt;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
