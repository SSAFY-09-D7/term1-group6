import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class cs_14502 {
    static int N, M;
    static int max;
    static int cnt;
    static int[][] map;
    static int[][] myMap;
    static class Point{
        int row, col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static Point[] pickWallList;
    static ArrayList<Point> canWallList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        myMap = new int[N][M];
        canWallList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) canWallList.add(new Point(i, j));
            }
        }
        pickWallList = new Point[3];
        max = 0;
        comb(0, 0);
        System.out.println(max);
    }

    private static void comb(int idx, int count) {
        if(count == 3){
            // 작업을 위한 새로운 배열 만들기
            drawMyMap();

            for(int i = 0; i < 3; i++){
                myMap[pickWallList[i].row][pickWallList[i].col] = 1; // 벽으로 바꾸기
            }
            // 벽 세 개를 세운 상태이므로 안전영역의 수 검사
            visited = new boolean[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(!visited[i][j] && myMap[i][j] == 2){
                        bfs(i, j);
                    }
                }
            }
            countSafePos();
//            for(int i = 0; i < 3; i++){
//                System.out.print(pickWallList[i].row + " " + pickWallList[i].col + " ");
//            }
//            System.out.println("cnt : " + cnt);
            if(max < cnt) max = cnt;
            return;
        }

        for(int i = idx; i < canWallList.size(); i++){
            pickWallList[count] = canWallList.get(i);
            comb(i + 1, count + 1);
        }
    }

    private static void countSafePos() {
        cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(myMap[i][j] == 0){
                    cnt += 1;
                }
            }
        }
    }

    private static void drawMyMap() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                myMap[i][j] = map[i][j];
            }
        }
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Point now = q.poll();
            myMap[now.row][now.col] = 2;

            for(int i = 0; i < 4; i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                if(!visited[nextRow][nextCol] && myMap[nextRow][nextCol] != 1){
                    visited[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol));
                }
            }
        }
    }
}
