import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, Size;
    public static int[][] arr;
    public static List<Node> list;
    public static int[] ans;    // 뽑힌 조합 인덱스 저장할 배열
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static boolean[][] visited;
    public static Queue<Node> queue;
    public static int MIN = Integer.MAX_VALUE;

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

        arr = new int[N][N];
        list = new ArrayList<>();
        ans = new int[M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    list.add(new Node(i, j, 0));
                }
            }
        }

        Size = list.size();

        // Size 중 M개 조합 뽑기
        combination(0, 0);

        if(MIN == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(MIN);
    }

    public static void combination(int idx, int k){
        if(k == M){

            // M개 다 뽑으면 bfs 돌릴 준비
            visited = new boolean[N][N];
            queue = new LinkedList<>();

            for(int i=0; i<M; i++){
                Node node = list.get(ans[i]);
                queue.add(node);
                visited[node.i][node.j] = true;
            }

            // newArr로 bfs 돌리기
            int c = bfs();
            if(c!=Integer.MAX_VALUE && MIN > c)
                MIN = c;

            return;
        }

        for(int i=idx; i<Size; i++){
            ans[k] = i;
            combination(i+1, k+1);
        }
    }

    public static int bfs(){
        int cnt = 0;
        while(!queue.isEmpty()){

            if(isEnd()){
                break;
            }

            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;
            int cntcnt = node.cnt;

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<N && (arr[iii][jjj]==0 || arr[iii][jjj]==2) && !visited[iii][jjj]){
                    visited[iii][jjj] = true;
                    queue.add(new Node(iii, jjj, cntcnt + 1));
                    if(cnt < cntcnt+1)
                        cnt = cntcnt+1;
                }
            }

        }

        // 마지막에 방문하지 않은 칸 있는지 또 확인
        if(isEnd())
            return cnt;
        else
            return Integer.MAX_VALUE;
    }

    public static boolean isEnd(){
        // arr이 0또는 2인데 방문하지 않은 칸 있으면 return false;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if((arr[i][j] == 0 || arr[i][j] == 2) && !visited[i][j])
                    return false;
            }
        }
        return true;
    }

}