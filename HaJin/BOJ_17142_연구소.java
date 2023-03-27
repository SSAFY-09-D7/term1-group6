import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, SIZE;
    public static int[][] arr;
    public static int empty = 0;
    public static boolean[][] visited;
    public static List<Node> list;
    public static Node[] ans;    // 뽑은 조합 저장
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int MIN = Integer.MAX_VALUE;  // 최소 시간

    public static class Node{
        int i;
        int j;
        int cnt;
        Node(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0){
                    empty += 1;
                }
                else if(arr[i][j] == 2) {
                    list.add(new Node(i, j, 0));
                }
            }
        }

        // M개 조합 뽑기
        ans = new Node[M];
        comb(0, 0);

        if(empty == 0)
            System.out.println(0);
        else{
            if(MIN == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(MIN);
        }


    }

    public static void comb(int idx, int k) {
        if(k == M){
            // M개 뽑은 후에 해당 위치 바이러스 활성상태로 (활성 -> 비활성 칸으로 가면 비활성 바이러스가 활성 바이러스로 변함)
            BFS();

            return;
        }

        for(int i=idx; i<list.size(); i++){
            ans[k] = list.get(i);
            comb(i + 1, k + 1);
        }
    }

    public static void BFS(){
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][N];
        int tmpEmpty = empty;

        for(int i=0; i<M; i++){
            Node node = ans[i];
            visited[node.i][node.j] = true;
            queue.add(node);
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int i = node.i;
            int j = node.j;
            int cnt = node.cnt;

            for(int p=0; p<4; p++){
                int ii = i + nr[p];
                int jj = j + nc[p];

                if(ii>=0 && ii<N && jj>=0 && jj<N && !visited[ii][jj] && arr[ii][jj]!=1){

                    if(arr[ii][jj] == 0){
                        tmpEmpty -= 1;
                    }
                    if(tmpEmpty == 0){
                        MIN = Math.min(MIN, cnt+1);
                        return;
                    }
                    visited[ii][jj] = true;
                    queue.add(new Node(ii, jj, cnt+1));

                }
            }
        }

    }

}