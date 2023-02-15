import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int count;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};

    static class Point{
        int row, col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row - 1][col - 1] = 1; // 0,0 좌표로 시작하게끔
        }

        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1){
                    count = 0;
                    dfs(i, j);
                    bfs(i, j);
                    if(max < count) max = count;
                }
            }
        }

        System.out.println(max);
    }

    private static void dfs(int row, int col) {
        visited[row][col] = true;
        count++;
        for(int i = 0; i < 4; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

            if(map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]){
                dfs(nextRow, nextCol);
            }
        }
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            count++;
            int curRow = p.row;
            int curCol = p.col;
            for(int i = 0; i < 4; i++){
                int nextRow = curRow + dRow[i];
                int nextCol = curCol + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1){
                    q.add(new Point(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }
}
