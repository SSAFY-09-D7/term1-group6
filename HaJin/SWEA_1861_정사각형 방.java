import java.io.*;
import java.util.*;

public class Solution {

    public static StringBuilder sb = new StringBuilder();
    static int N;

    static int[][] arr;

    static int[] r = {-1, 1, 0, 0};
    static int[] c = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max_cnt = 0;
            int max_num = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {

                    int cnt = bfs(i, j);

                    if(max_cnt < cnt) {
                        max_cnt = cnt;
                        max_num = arr[i][j];
                    }
                    if(max_cnt == cnt){
                        if(max_num > arr[i][j]){
                            max_num = arr[i][j];
                        }
                    }
                }
            }
            System.out.printf("#%d %d %d\n", test_case, max_num, max_cnt);
        }
    }

    public static int bfs(int i, int j){
        Queue<Node> queue = new LinkedList<>();

        int cnt = 0;
        queue.add(new Node(i, j));
        cnt += 1;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int p=0; p<4; p++) {
                int ii = node.i + r[p];
                int jj = node.j + c[p];

                if(ii>=0 && ii<N && jj>=0 && jj<N && arr[ii][jj] == arr[node.i][node.j]+1) {
                    queue.add(new Node(ii, jj));
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    static class Node{
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}