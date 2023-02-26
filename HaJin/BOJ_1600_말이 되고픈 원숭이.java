import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int[] hr = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] hc = {-1, 1, -2, 2, -2, 2, -1, 1};
    public static int[] mr = {-1, 1, 0, 0};
    public static int[] mc = {0, 0, -1, 1};

    public static int K, W, H;  // 말 점프 K번 가능
    public static int[][] arr;
    public static boolean[][][] visited;
    public static int MIN = -1;

    public static class Node{
        int i;
        int j;
        int k;  // 현재까지 말 점프 횟수
        int cnt;
        Node(int i, int j, int k, int cnt){
            this.i = i;
            this.j = j;
            this.k = k;
            this.cnt = cnt;
        }
    }
    public static void func(int x, int y){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(x, y, 0, 0));
        for(int i=0; i<K+1; i++){
            visited[x][y][i] = true;
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;
            int kk = node.k;
            int ccnntt = node.cnt;

            if(MIN != -1 && node.cnt > MIN)
                continue;

            if(ii == H-1 && jj == W-1){
                if(MIN == -1)
                    MIN = ccnntt;
                else{
                    if(MIN > ccnntt)
                        MIN = ccnntt;
                }
                return;
            }

            // 말 점프 횟수 남은 경우
            if(kk < K){
                // 8방 탐색
                for(int i=0; i<8; i++){
                    int iii = ii + hr[i];
                    int jjj = jj + hc[i];

                    if(iii>=0 && iii<H && jjj>=0 && jjj<W && arr[iii][jjj]==0 && !visited[iii][jjj][kk+1]){
                        queue.add(new Node(iii, jjj, kk+1, ccnntt+1));
                        visited[iii][jjj][kk+1] = true;
                    }
                }
            }

            // 말 점프 횟수 남은 경우와 남지 않은 경우 모두 4방 탐색
            for (int i = 0; i < 4; i++) {
                int iii = ii + mr[i];
                int jjj = jj + mc[i];

                if (iii >= 0 && iii < H && jjj >= 0 && jjj < W && arr[iii][jjj] == 0 && !visited[iii][jjj][kk]) {
                    queue.add(new Node(iii, jjj, kk, ccnntt + 1));
                    visited[iii][jjj][kk] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());   // 가로길이
        H = Integer.parseInt(st.nextToken());   // 세로길이

        arr = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0, 0);

        System.out.println(MIN);
    }
}
