import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    // 상 우 하 좌
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while(true){
            // 현재 칸이 청소되어 있지 않은 경우 청소한다.
            if(map[row][col] == 0){
                result += 1;
                map[row][col] = -1; // 청소한 곳의 경우 -1로 설정
            }

            boolean existClearPoint = false;
//            System.out.println("현재 좌표");
//            System.out.println("row : " + row + " col : " + col + " dir : " + dir);
            // 현재 칸 기준 주변 4칸 판별
            for(int i = 0; i < 4; i++){
                int nextRow = row + dRow[i];
                int nextCol = col + dCol[i];
                if(map[nextRow][nextCol] == 0){
                    existClearPoint = true;
                }
            }
            // 주변 4칸에 청소할 수 있는 칸이 없는 경우
            if(!existClearPoint){
                // 벽이면 작동을 멈춘다
                if(map[row + dRow[(dir + 2) % 4]][col + dCol[(dir + 2) % 4]] == 1){
                    break;
                }
                // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                else{
                    // 후진 시작
//                    System.out.println("후진 시작");
//                    System.out.println("row : " + row + " col : " + col + " dir : " + dir);
                    row += dRow[(dir + 2) % 4];
                    col += dCol[(dir + 2) % 4];
//                    System.out.println("후진 후");
//                    System.out.println("row : " + row + " col : " + col + " dir : " + dir);
                }
            }
            // 주변 4칸에 청소할 수 있는 칸이 있는 경우
            else{
                // 반시계 방향으로 회전
                dir = changedir(dir);
                // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                if(map[row + dRow[dir % 4]][col + dCol[dir % 4]] == 0){
                    row += dRow[dir % 4];
                    col += dCol[dir % 4];
                }
            }
        }
        System.out.println(result);
    }

    private static int changedir(int cur) {
        if(cur == 0){
            return 3;
        }
        if(cur == 1){
            return 0;
        }
        if(cur == 2){
            return 1;
        }
        if(cur == 3){
            return 2;
        }
        return -1;
    }
}
