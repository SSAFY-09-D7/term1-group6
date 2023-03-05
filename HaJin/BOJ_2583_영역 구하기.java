import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M, K;
    public static int[][] arr;

    public static class Node{
        int i;
        int j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    public static boolean[][] visited;
    public static List<Integer> list;

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<>();

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            x2 -= 1;
            y2 -= 1;

            for(int p=0; p<=x2-x1; p++){
                for(int q=0; q<=y2-y1; q++){
                    arr[x1+p][y1+q] = 1;
                }
            }

        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && arr[i][j] == 0)
                    list.add(bfs(i, j));
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

    }

    public static int bfs(int i, int j){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(i, j));
        visited[i][j] = true;
        int cnt = 1;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;

            for(int p=0; p<4; p++){
                int iii = ii + nr[p];
                int jjj = jj + nc[p];

                if(iii>=0 && iii<N && jjj>=0 && jjj<M && !visited[iii][jjj] && arr[iii][jjj]==0){
                    queue.add(new Node(iii, jjj));
                    visited[iii][jjj] = true;
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

}