import java.io.*;
import java.util.*;

public class cs_s5656{
    static int N, W, H;
    static int leftCount;
    static int min, count;
    static int[] sel;
    static int[][] map;
    static int[][] tmpMap;
    static Queue<Point> q;
    static Queue<Integer> gravity;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static class Point{
        int row, col, value;
        public Point(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            tmpMap = new int[H][W];
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            gravity = new LinkedList<>();
            q =  new LinkedList<>();
            min = Integer.MAX_VALUE;
            sel = new int[N];
            combR(0);
            System.out.println("#" + testCase + " " + min);
        }
    }

    private static void combR(int idx) {
        if(idx == N) {
            leftCount = 0;
            initTmpMap();

            for(int cnt = 0; cnt < N; cnt++){
                int dropCol = sel[cnt];

                for(int i = 0; i < H; i++){
                    if(tmpMap[i][dropCol] != 0){
                        q.add(new Point(i, dropCol, tmpMap[i][dropCol]));
                        tmpMap[i][dropCol] = 0;
                        break;
                    }
                }
                bfs();
                fillEmptySpace();
            }
            count = countMap();

            if(count < min) min = count;
            return;
        }

        for(int i = 0; i < W; i++) {
            sel[idx] = i;
            combR(idx + 1);
        }
    }

    private static void bfs() {
        while(!q.isEmpty()){
            Point now = q.poll();

            for(int i = 0; i < 4; i++) {
                for(int s = 1; s < now.value; s++) {
                    int nextRow = now.row + dRow[i] * s;
                    int nextCol = now.col + dCol[i] * s;

                    if(nextRow < 0 || nextCol < 0 || nextRow >= H || nextCol >= W || tmpMap[nextRow][nextCol] == 0) continue;

                    q.add(new Point(nextRow, nextCol, tmpMap[nextRow][nextCol]));
                    tmpMap[nextRow][nextCol] = 0;
                }
            }
        }
    }

    private static int countMap() {
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(tmpMap[i][j] > 0) leftCount++;
            }
        }
        return leftCount;
    }

    private static void fillEmptySpace() {
        for(int j = 0; j < W; j++) {
            for(int i = H - 1; i >= 0; i--) {
                if(tmpMap[i][j] > 0) {
                    gravity.add(tmpMap[i][j]);
                    tmpMap[i][j] = 0;
                }
            }
            for(int i = H - 1; i >= 0; i--) {
                if(gravity.isEmpty()) break;
                else tmpMap[i][j] = gravity.poll();
            }
        }
    }

    private static void initTmpMap() {
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }
    }
}