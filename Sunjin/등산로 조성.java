import java.io.*;
import java.util.*;


public class cs_s1949{
    static int N, K;
    static int max;
    static int[][] map;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static int result;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int max = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(max < map[i][j]) max = map[i][j];
                }
            }

            result = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == max) {
                        visited = new boolean[N][N];
                        visited[i][j] = true;
                        dfs(i, j, 1, 1);
                    }
                }
            }
            System.out.println("#" + testCase + " " + result);
        }
    }
    private static void dfs(int row, int col, int breakCount, int depth) {
        // basis part
		if(result < depth) {
			result = depth;
		}

        // inductive part
        for(int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;

            // 방문하지 않은 지역
            if(!visited[nextRow][nextCol]) {
                // 높아서 원래는 갈 수 없음
                if(map[row][col] <= map[nextRow][nextCol]) {
                    // 부실 수 있는 기회가 없음
                    if(breakCount == 0) continue;
                        // 부실 수 있는 기회가 있음
                    else {
                        int diff = map[nextRow][nextCol] - map[row][col];
                        if(diff < K) {
                            int tmp = map[nextRow][nextCol];
                            map[nextRow][nextCol] = map[row][col] - 1;
                            visited[nextRow][nextCol] = true;
                            dfs(nextRow, nextCol, breakCount - 1, depth + 1);
                            visited[nextRow][nextCol] = false;
                            map[nextRow][nextCol] = tmp;
                        }
                    }
                }
                // 그냥 갈 수 있음
                else {
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, breakCount, depth + 1);
                    visited[nextRow][nextCol] = false;
                }
            }
        }
    }
}