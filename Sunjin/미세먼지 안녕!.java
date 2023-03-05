import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static int[] firstdRow = {0, 1, 0, -1};
    static int[] firstdCol = {1, 0, -1, 0};
    static int[] seconddRow = {0, -1, 0, 1};
    static int[] seconddCol = {1, 0, -1, 0};
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
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        Point[] airConditioner = new Point[2];
        int airConditionerCount = 0;
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    airConditioner[airConditionerCount++] = new Point(i, j);
                }
            }
        }

        for(int time = 0; time < T; time++){
            int[][] tmp = new int[R][C];
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    tmp[i][j] = map[i][j];
                }
            }
            Queue<Point> q = new LinkedList<>();
            visited = new boolean[R][C];
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(map[i][j] != 0 && map[i][j] != -1) {
                        q.add(new Point(i, j));
                    }
                }
            }

            // 확산
            while(!q.isEmpty()){
                Point now = q.poll();
                if(visited[now.row][now.col]) continue;

                ArrayList<Point> addList = new ArrayList<>();
                for(int i = 0; i < 4; i++){
                    int nextRow = now.row + dRow[i];
                    int nextCol = now.col + dCol[i];
                    
                    // 지도 밖이라면 확산 안됨
                    if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;

                    // 왼쪽이 공기청정기라면 확산 안됨
                    if(map[nextRow][nextCol] == -1) continue;

                    addList.add(new Point(nextRow, nextCol));
                    q.add(new Point(nextRow, nextCol));
                }
                if(addList.size() != 0){
                    for(Point next : addList){
                        tmp[next.row][next.col] += map[now.row][now.col] / 5;
                        tmp[now.row][now.col] -= map[now.row][now.col] / 5;
                    }
                }
                visited[now.row][now.col] = true;
            }

            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    map[i][j] = tmp[i][j];
                }
            }
            
            //공기청정기 작동
            int row = 0;
            int col = 0;
            int tempValue = map[0][0];
            int dir = 0;
            while(dir < 4){
                int nextRow = row + firstdRow[dir];
                int nextCol = col + firstdCol[dir];

                if(nextRow < 0 || nextCol < 0 || nextRow > airConditioner[0].row || nextCol >= C){
                    dir++;
                }
                else {
                    map[row][col] = map[nextRow][nextCol];
                    row = nextRow;
                    col = nextCol;
                }
            }
            map[1][0] = tempValue;
            map[airConditioner[0].row][1] = 0;
            map[airConditioner[0].row][airConditioner[0].col] = -1;

            row = R - 1;
            col = 0;
            tempValue = map[row][0];
            dir = 0;
            while(dir < 4){
                int nextRow = row + seconddRow[dir];
                int nextCol = col + seconddCol[dir];

                if(nextRow < airConditioner[1].row || nextCol < 0 || nextRow >= R || nextCol >= C){
                    dir++;
                }
                else {
                    map[row][col] = map[nextRow][nextCol];
                    row = nextRow;
                    col = nextCol;
                }
            }
            map[R - 2][0] = tempValue;
            map[airConditioner[1].row][1] = 0;
            map[airConditioner[1].row][airConditioner[1].col] = -1;

        }

        System.out.println(print());
    }

    private static int print() {
        int count = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] != 0 && map[i][j] != -1){
                    count += map[i][j];
                }
            }
        }
        return count;
    }
}
