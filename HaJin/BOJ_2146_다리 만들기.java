import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static int N;
    public static int[][] arr;
    public static boolean[][] visited;

    public static int MIN = Integer.MAX_VALUE;

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};


    public static class Land{
        int i;
        int j;
        Land(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static class Ocean{
        int startLand;
        int i;
        int j;
        int cnt;
        Ocean(int startLand, int i, int j, int cnt){
            this.startLand = startLand;
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        // 배열 입력받기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        // 육지 번호 붙이기
        int landNum = 2;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    landLabel(i, j, landNum);
                    landNum += 1;
                }
            }
        }

        // 주변에 바다가 있는 육지만 bfs 돌리기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(nearOcean(i, j) && arr[i][j] != 0) {
                    visited = new boolean[N][N];
                    Search(arr[i][j], i, j, 0);
                }
            }
        }

        System.out.println(MIN);

    }

    public static void landLabel(int i, int j, int landNum) {

        Queue<Land> queue = new LinkedList<>();

        arr[i][j] = landNum;
        queue.add(new Land(i, j));

        while(!queue.isEmpty()) {
            Land land = queue.poll();
            int ii = land.i;
            int jj = land.j;

            for(int p=0; p<4; p++) {
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<N && !visited[iii][jjj] && arr[iii][jjj] == 1) {
                    visited[iii][jjj] = true;
                    arr[iii][jjj] = landNum;
                    queue.add(new Land(iii, jjj));
                }
            }
        }

    }

    public static boolean nearOcean(int i, int j) {

        for(int p=0; p<4; p++) {
            int ii = i + nr[p];
            int jj = j + nc[p];

            if(ii>=0 && ii<N && jj>=0 && jj<N && arr[ii][jj] == 0)
                return true;
        }

        return false;
    }

    public static void Search(int startLand, int i, int j, int cnt) {

        Queue<Ocean> queue = new LinkedList<>();

        queue.add(new Ocean(startLand, i, j, cnt));

        while(!queue.isEmpty()) {
            Ocean ocean = queue.poll();
            int ii = ocean.i;
            int jj = ocean.j;
            int ssttaarrtt = ocean.startLand;
            int ccnntt = ocean.cnt;

            // 4방탐색 후 다른 육지와 닿아있으면 MIN 비교
			if(nearLand(ssttaarrtt, ii, jj)) {
				if(MIN > ccnntt) {
					MIN = ccnntt;
				}
			}

            if(ccnntt > MIN)
                continue;

            for(int p=0; p<4; p++) {
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<N && arr[iii][jjj]==0 && !visited[iii][jjj]) {
                    visited[iii][jjj] = true;
                    queue.add(new Ocean(ssttaarrtt, iii, jjj, ccnntt+1));
                }
            }
        }
    }

    public static boolean nearLand(int startLand, int i, int j) {

        for(int p=0; p<4; p++) {
            int ii = i + nr[p];
            int jj = j + nc[p];

            if(ii>=0 && ii<N && jj>=0 && jj<N && arr[ii][jj]!=0 && arr[ii][jj]!=startLand)
                return true;
        }

        return false;
    }
}
