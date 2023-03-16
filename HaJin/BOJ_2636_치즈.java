import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr;
    public static boolean[][] newArr;
    public static boolean[][] visited;
    public static boolean[][] isOne;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int LABEL = 10;    // 녹는 시간 저장
    public static int cheeseCNT = 0;
    public static class Node{
        int i;
        int j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        isOne = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 1){
                    cheeseCNT += 1;
                    isOne[i][j] = true;
                }
                    
            }
        }

        visited = new boolean[N][M];
        // 테두리 라벨링
        edgeBFS(0, 0);

        LABEL += 1;

        while(true) {
            // 치즈 남아있는지 확인
            if(cheeseCNT == 0) {
                break;
            }
            newArr = new boolean[N][M];

            edgeBFS(0, 0);

            // 테두리 녹이기
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(arr[i][j] == 1 && isEdge(i, j)) {
                        newArr[i][j] = true;
                        cheeseCNT -= 1;
                    }
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(newArr[i][j])
                        arr[i][j] = 0;
                }
            }
            visited = new boolean[N][M];
            LABEL += 1;
        }

        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j] == 0 && isOne[i][j])
                    cnt += 1;
            }
        }

        System.out.println(LABEL-11);
        System.out.println(cnt);
    }

    public static void edgeBFS(int i, int j) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(i, j));
        visited[0][0] = true;
        arr[0][0] = 10;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;

            for(int p=0; p<4; p++) {
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<M && !visited[iii][jjj] && (arr[iii][jjj]==0 || arr[iii][jjj]==10)) {
                    visited[iii][jjj] = true;
                    arr[iii][jjj] = 10;
                    queue.add(new Node(iii, jjj));
                }
            }
        }
    }

    public static boolean isEdge(int i, int j) {

        for(int p=0; p<4; p++) {
            int ii = i + nr[p];
            int jj = j + nc[p];

            if(ii>=0 && ii<N && jj>=0 && jj<M && arr[ii][jj]==10) {
                return true;
            }
        }

        return false;
    }
}