import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14716_현수막 {
    static class Point {
        int r,c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int M,N,cnt;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {-1,-1,0,1,1,1,0,-1};
    static int[] dc = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());   //세로
        N = Integer.parseInt(st.nextToken());   //가로

        map = new int[M][N];
        v = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !v[i][j]) {
                    dfs(new Point(i,j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

    }
    private static void dfs(Point p) {
        v[p.r][p.c]=true;
        for (int d = 0; d < 8; d++) {
            int nr = p.r + dr[d];
            int nc = p.c + dc[d];

            if(nr<0 || nr>=M || nc<0 || nc>=N ||v[nr][nc] || map[nr][nc] == 0) continue;

            dfs(new Point(nr,nc));

        }
    }
}
