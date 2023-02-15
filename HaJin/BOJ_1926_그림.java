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

    public static int n, m;
    public static int[][] arr;
    public static boolean[][] visited;

    public static int[] a = {0, 0, -1, 1};
    public static int[] b = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int max_size = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[i][j] == 1 && !visited[i][j]){
                    cnt += 1;
                    visited[i][j] = true;
                    int tmp = bfs(i, j, 1);
                    if(max_size < tmp)
                        max_size = tmp;
                }
            }
        }
        System.out.println(cnt);
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

                if(xx>=0 && yy>=0 && xx<n && yy<m && !visited[xx][yy] && arr[xx][yy] == 1){
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