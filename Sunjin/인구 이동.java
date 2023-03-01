import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static class Point{
        int row, col, value;
        public Point(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while(true){
            int count = 0;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j]) {
                        bfs(i, j, map[i][j]);
                        count++;
                    }
                }
            }
            if(count == N * N) {
                break;
            }
            result += 1;
        }
        System.out.println(result);
    }

    private static void bfs(int row, int col, int value) {
        ArrayList<Point> openList = new ArrayList<>();
        int personSum = 0;

        Queue<Point> q = new LinkedList<>();
        visited[row][col] = true;
        q.add(new Point(row, col, value));

        while(!q.isEmpty()){
            Point now = q.poll();
            openList.add(now);
            personSum += now.value;

            for(int i = 0; i < 4; i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;

                if(!visited[nextRow][nextCol]){
                    int diff = Math.abs(now.value - map[nextRow][nextCol]);
                    if(L <= diff && diff <= R){
                        visited[nextRow][nextCol] = true;
                        q.add(new Point(nextRow, nextCol, map[nextRow][nextCol]));
                    }
                }
            }
        }
        int dividePerson = personSum / openList.size();
        for(Point p : openList){
            map[p.row][p.col] = dividePerson;
        }
    }
}
