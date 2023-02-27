import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class cs_3184 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int wolfCount, sheepCount;
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
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int sheepResult = 0;
        int wolfResult = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] != '#' && !visited[i][j]){
                    wolfCount = sheepCount = 0;
                    bfs(i, j);
                    if(wolfCount < sheepCount){
                        sheepResult += sheepCount;
                    }
                    else{
                        wolfResult += wolfCount;
                    }
                }
            }
        }
        System.out.print(sheepResult + " " + wolfResult);
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();

        visited[row][col] = true;
        q.add(new Point(row, col));

        while(!q.isEmpty()){
            Point now = q.poll();
            if(map[now.row][now.col] == 'v') wolfCount++;
            if(map[now.row][now.col] == 'o') sheepCount++;

            for(int i = 0; i < 4; i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;

                if(!visited[nextRow][nextCol] && map[nextRow][nextCol] != '#'){
                    visited[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol));
                }
            }
        }

    }
}
