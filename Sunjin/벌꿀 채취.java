import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cs_s2115 {
    static int N, M, C;
    static int[][] map;
    static int result;
    static int[] eachHoneyMax;
    static boolean[] getHoneyList;
    static boolean[][] visited;
    static Point[] sel;
    static ArrayList<Point> startList;
    static class Point{
        int row, col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            startList = new ArrayList<>();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N - M + 1; j++){
                    startList.add(new Point(i, j));
                }
            }

            sel = new Point[2];
            result = Integer.MIN_VALUE;
            comb(0, 0);
            System.out.println("#" + testCase + " " + result);
        }
    }

    private static void comb(int idx, int count) {
        if(count == 2){
            // 선택 완료
//            System.out.println(Arrays.toString(sel));
            // 뽑은 조합을 기준으로 부분집합 뽑기
            eachHoneyMax = new int[2];
            int getHoneySum = 0;
            getHoneyList = new boolean[M];
            for(int i = 0; i < sel.length; i++){
                eachHoneyMax[i] = 0;
                getHoney(sel[i], 0, i);
                getHoneySum += eachHoneyMax[i];
            }
            if(result < getHoneySum) result = getHoneySum;
            return;
        }

        for(int i = idx; i < startList.size(); i++){
            boolean flag = false;
            for(int j = startList.get(i).col; j < startList.get(i).col + M; j++){
                if(visited[startList.get(i).row][j]) {
                   flag = true;
                }
            }
            if(!flag){
                for(int j = startList.get(i).col; j < startList.get(i).col + M; j++){
                    visited[startList.get(i).row][j] = true;
                }
                sel[count] = startList.get(i);
                comb(i + 1, count + 1);
                for(int j = startList.get(i).col; j < startList.get(i).col + M; j++){
                    visited[startList.get(i).row][j] = false;
                }
            }
//            else{
//                comb(i + 1, count);
//            }
        }
    }

    private static void getHoney(Point startPoint, int idx, int selIndex) {
        if(idx == M){
            //부분집합 뽑기 완료
            int honeySum = 0;
            int honeyResult = 0;
            for(int i = 0; i < getHoneyList.length; i++){
                if(getHoneyList[i]) {
                    honeySum += map[startPoint.row][startPoint.col + i];
                    honeyResult += map[startPoint.row][startPoint.col + i] * map[startPoint.row][startPoint.col + i];
                }
            }
            if(honeySum <= C){
                if(eachHoneyMax[selIndex] < honeyResult) eachHoneyMax[selIndex] = honeyResult;
            }
            return;
        }

        getHoneyList[idx] = true;
        getHoney(startPoint, idx + 1, selIndex);

        getHoneyList[idx] = false;
        getHoney(startPoint, idx + 1, selIndex);
    }
}
