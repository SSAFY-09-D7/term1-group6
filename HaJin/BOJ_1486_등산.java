import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int N, M, T, D;
    public static int[][] arr;
    public static int[][] goTime;
    public static int[][] backTime;
    public static int[][] tmp;
    public static boolean[][] visited;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static PriorityQueue<Node> pq;

    public static class Node implements Comparable<Node>{
        int i;
        int j;
        int time;

        Node(int i, int j, int time){
            this.i = i;
            this.j = j;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<M; j++) {
                if(s.charAt(j) >= 'a' && s.charAt(j) <= 'z')
                    arr[i][j] = s.charAt(j) - '0' - 23;
                else
                    arr[i][j] = s.charAt(j) - '0' - 17;
            }
        }

        visited = new boolean[N][M];
        goTime = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                goTime[i][j] = Integer.MAX_VALUE;
            }
        }

        // 호텔 -> 모든 정점 최단시간
        goM();

        // 모든 정점 -> 호텔 최단시간
        backTime = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited = new boolean[N][M];

                tmp = new int[N][M];
                for(int ii=0; ii<N; ii++){
                    for(int jj=0; jj<M; jj++){
                        tmp[ii][jj] = Integer.MAX_VALUE;
                    }
                }

                goH(i, j);

                backTime[i][j] = tmp[0][0];

            }
        }

        // goTime + backTime <= D 인 값들 중 최댓값 찾기
        int max = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(goTime[i][j] != Integer.MAX_VALUE && backTime[i][j] != Integer.MAX_VALUE &&goTime[i][j] + backTime[i][j] <= D && arr[i][j] > max){
                    max = arr[i][j];
                }
            }
        }

        System.out.println(max);
    }

    public static void goM() {
        pq = new PriorityQueue<>();

        pq.add(new Node(0, 0, 0));
        goTime[0][0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int i = node.i;
            int j = node.j;
            int time = node.time;

            if(visited[i][j])
                continue;

            visited[i][j] = true;

            for(int p=0; p<4; p++) {
                int ii = i + nr[p];
                int jj = j + nc[p];

                if(ii>=0 && ii<N && jj>=0 && jj<M) {
                    int diff = Math.abs(arr[i][j] - arr[ii][jj]);
                    if(diff <= T) {
                        int t;
                        if(arr[i][j] >= arr[ii][jj])
                            t = 1;
                        else
                            t = (int)Math.pow(diff, 2);

                        if(goTime[ii][jj] > goTime[i][j] + t) {
                            goTime[ii][jj] = goTime[i][j] + t;
                            pq.add(new Node(ii, jj, time + t));
                        }
                    }
                }
            }
        }
    }
    
    public static void goH(int i, int j) {
        pq = new PriorityQueue<>();

        pq.add(new Node(i, j, 0));
        tmp[i][j] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int ii = node.i;
            int jj = node.j;
            int time = node.time;

            if(visited[ii][jj])
                continue;

            visited[ii][jj] = true;

            for(int p=0; p<4; p++) {
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<M) {
                    int diff = Math.abs(arr[ii][jj] - arr[iii][jjj]);
                    if(diff <= T) {
                        int t;
                        if(arr[ii][jj] >= arr[iii][jjj])
                            t = 1;
                        else
                            t = (int)Math.pow(diff, 2);

                        if(tmp[iii][jjj] > tmp[ii][jj] + t) {
                            tmp[iii][jjj] = tmp[ii][jj] + t;
                            pq.add(new Node(iii, jjj, time + t));
                        }
                    }
                }
            }
        }

    }

}