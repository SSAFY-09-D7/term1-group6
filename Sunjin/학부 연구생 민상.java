import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] windCome;
    // 상 하 좌 우
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static class Info{
        int row, col, dir;
        public Info(int row, int col, int dir){
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        windCome = new boolean[N][M];

        ArrayList<Info> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    list.add(new Info(i,j,-1));
                }
            }
        }

        for(int i = 0; i < list.size(); i++){
            // 에어컨 위치 바람 처리
            windCome[list.get(i).row][list.get(i).col] = true;
            bfs(list.get(i).row, list.get(i).col);
        }

        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(windCome[i][j]) result++;
            }
        }
        System.out.println(result);
    }

    private static void bfs(int row, int col) {
        Queue<Info> q = new LinkedList<>();

        // 초기 4방향 바람 뿌리기
        for(int i = 0; i < 4; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >=M) continue;

            q.add(new Info(nextRow, nextCol, i));
        }

        while(!q.isEmpty()){
            Info now = q.poll();
//            System.out.println("q에서 꺼낸 현재 좌표");
//            System.out.println("row : " + now.row + " col : " + now.col + " dir : " + now.dir);
            windCome[now.row][now.col] = true;

            int nextRow = -1;
            int nextCol = -1;
            int nextDir = now.dir;

//            System.out.println("현재 좌표의 자리 값 : " + map[now.row][now.col]);
            // 에어컨 처리를 한다면 여기서
            if(map[now.row][now.col] == 0){
                nextRow = now.row + dRow[now.dir];
                nextCol = now.col + dCol[now.dir];
            }
            else if(map[now.row][now.col] == 1){
                if(now.dir == 0 || now.dir == 1){
                    nextRow = now.row + dRow[now.dir];
                    nextCol = now.col + dCol[now.dir];
                }
            }
            else if(map[now.row][now.col] == 2){
                if(now.dir == 2 || now.dir == 3){
                    nextRow = now.row + dRow[now.dir];
                    nextCol = now.col + dCol[now.dir];
                }
            }
            else if(map[now.row][now.col] == 3){
                if(now.dir == 0){
                    nextDir = 3;
                }
                else if(now.dir == 1){
                    nextDir = 2;
                }
                else if(now.dir == 2){
                    nextDir = 1;
                }
                else if(now.dir == 3){
                    nextDir = 0;
                }
                nextRow = now.row + dRow[nextDir];
                nextCol = now.col + dCol[nextDir];
            }
            else if(map[now.row][now.col] == 4){
                if(now.dir == 0){
                    nextDir = 2;
                }
                else if(now.dir == 1){
                    nextDir = 3;
                }
                else if(now.dir == 2){
                    nextDir = 0;
                }
                else if(now.dir == 3){
                    nextDir = 1;
                }
                nextRow = now.row + dRow[nextDir];
                nextCol = now.col + dCol[nextDir];
            }

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
//            System.out.println("q에 넣을 다음 좌표");
//            System.out.println("row : " + nextRow + " col : " + nextCol + " dir : " + nextDir);
            q.add(new Info(nextRow, nextCol, nextDir));
        }
    }
}
