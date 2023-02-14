import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, size;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};

    static class Point{
        int row,col;

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

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int max = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    count++;
                    max = Math.max(max, size);
                }
            }
        }
        sb.append(count + "\n" + max);
        System.out.println(sb);
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        visited[row][col] = true;
        size = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
            size++;

            for(int i = 0; i < 4; i++){
                int nextRow = p.row + dRow[i];
                int nextCol = p.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                if(map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]){
                    q.add(new Point(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }
}
