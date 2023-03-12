import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class cs_2573 {
    static int N, M;
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
    static class ChangePoint{
        int row, col, minusValue;
        public ChangePoint(int row, int col, int minusValue){
            this.row = row;
            this.col = col;
            this.minusValue = minusValue;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        boolean flag = false;
        while(!isAllSea()){
            // 빙산 녹이기
            meltIce();
            // 빙산이 몇개로 나뉘었는지
            int count = countIce();
            result++;
            if(count >= 2){
                flag = true;
                break;
            }
        }
        if(flag) System.out.println(result);
        else System.out.println(0);
    }

    private static void meltIce() {
        Queue<ChangePoint> q = new LinkedList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0){
                    int minusVal = findMinusValue(i, j);
                    q.add(new ChangePoint(i, j, minusVal));
                }
            }
        }

        while(!q.isEmpty()){
            ChangePoint now = q.poll();
            map[now.row][now.col] = map[now.row][now.col] - now.minusValue;
            if(map[now.row][now.col] < 0) map[now.row][now.col] = 0;
        }
    }

    private static int findMinusValue(int row, int col) {
        int seaCount = 0;
        for(int i = 0; i < 4; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

            if(map[nextRow][nextCol] == 0) seaCount++;
        }
        return seaCount;
    }

    private static int countIce() {
        int cnt = 0;
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Point now = q.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                if(!visited[nextRow][nextCol] && map[nextRow][nextCol] != 0){
                    visited[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean isAllSea() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                // 바다가 아니라면
                if (map[i][j] != 0) {
                    return false;
                }
        }
        return true;
    }
}
