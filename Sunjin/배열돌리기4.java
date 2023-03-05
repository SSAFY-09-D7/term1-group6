import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int sum, min;
    static int[][] tmp;
    static int[][] map;
    static int[] sel;
    static boolean[] visited;
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static RotateInfo[] rotateInfos;
    static class RotateInfo{
        int r, c, s;
        public RotateInfo(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateInfos = new RotateInfo[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            rotateInfos[i] = new RotateInfo(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        sel = new int[K];
        visited = new boolean[K];
        min = Integer.MAX_VALUE;
        perm(0);
        System.out.println(min);
    }

    private static void perm(int idx) {
        if(idx == rotateInfos.length){
            // 순열 뽑기 완료
//            System.out.println(Arrays.toString(sel));
            makeTmpArray(); // 임시 배열 만들기
            rotatetmpMap(); // 배열 돌리기
            isMin();// 배열의 최솟값 구하기
            return;
        }

        for(int i = 0; i < rotateInfos.length; i++){
            if(!visited[i]){
                visited[i] = true;
                sel[idx] = i;
                perm(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static void isMin() {
        for(int i = 0; i < N; i++){
            sum = 0;
            for(int j = 0; j < M; j++){
                sum += tmp[i][j];
            }
            if(sum < min) min = sum;
        }
    }

    private static void rotatetmpMap() {
        // 총 돌려야하는 순서 순열
        for(int count = 0; count < sel.length; count++){
            RotateInfo curInfo = rotateInfos[sel[count]];
            // 돌려야하는 네모 수
            for(int boxCount = 0; boxCount < curInfo.s; boxCount++){
                int startRow = curInfo.r - curInfo.s + boxCount;
                int startCol = curInfo.c - curInfo.s + boxCount;
                int endRow = curInfo.r + curInfo.s - boxCount;
                int endCol = curInfo.c + curInfo.s - boxCount;

                int curRow = startRow;
                int curCol = startCol;
                int startValue = tmp[curRow][curCol];
                int dir = 0;
                while(dir < 4){
                    int nextRow = curRow + dRow[dir];
                    int nextCol = curCol + dCol[dir];
                    if(nextRow < startRow || nextCol < startCol || nextRow > endRow || nextCol > endCol) {
                        dir++;
                    }
                    else{
//                        System.out.println("바꿀 값 : (" + curRow + ", " + curCol + ") " + tmp[curRow][curCol] + " -> " + "(" + nextRow + ", " + nextCol + ") " + tmp[nextRow][nextCol]);
                        tmp[curRow][curCol] = tmp[nextRow][nextCol];
                        curRow = nextRow;
                        curCol = nextCol;
                    }
                }
                tmp[startRow][startCol + 1] = startValue;
            }
//            System.out.println();
//            print();
//            System.out.println();
        }
    }

    private static void makeTmpArray() {
        tmp = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                tmp[i][j] = map[i][j];
            }
        }
    }

    private static void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(tmp[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
