import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int max;
    static char[][] map;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static class Point{
        int row, col, depth;
        public Point(int row, int col, int depth){
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = str.charAt(j);
            }
        }

        max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 'L'){
                    visited = new boolean[N][M];
                    int diff = bfs(i, j);
                    if(max < diff){
                        max = diff;
                    }
                }
            }
        }
        System.out.println(max);
    }

    private static int bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        visited[row][col] = true;
        q.add(new Point(row, col, 0));

        Point now = null;
        while (!q.isEmpty()) {
            now = q.poll();

            for(int i = 0; i < 4; i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 'L'){
                    visited[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol, now.depth + 1));
                }
            }
        }
        return now.depth;
    }
}
