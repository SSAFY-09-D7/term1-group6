import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr;
    public static boolean[][][] visited;    // i, j, dir   // 0:상,  1:하,  2:좌,  3:우

    public static class Node{
        int i;
        int j;
        int dir;    // 0:상,  1:하,  2:좌,  3:우

        Node(int i, int j, int dir){
            this.i = i;
            this.j = j;
            this.dir = dir;
        }
    }

    public static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M][4];


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 9){
                    queue.add(new Node(i, j, 0));
                    queue.add(new Node(i, j, 1));
                    queue.add(new Node(i, j, 2));
                    queue.add(new Node(i, j, 3));

                    for(int p=0; p<4; p++){
                        visited[i][j][p] = true;
                    }

                }
            }
        }

        func();


        int ans = 0;


        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j][0] || visited[i][j][1] || visited[i][j][2] || visited[i][j][3]){
                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }

    public static void func(){

        while(!queue.isEmpty()){

            Node node = queue.poll();
            int ii = node.i;
            int jj = node.j;
            int dir = node.dir;

            // 상
            if(dir == 0){
                int iii = ii - 1;
                int jjj = jj;

                if(iii>=0 && (arr[iii][jjj]==0 || arr[iii][jjj]==9) && !visited[iii][jjj][0]){
                    visited[iii][jjj][0] = true;
                    queue.add(new Node(iii, jjj, 0));
                }

                if(iii>=0 && (arr[iii][jjj]==1 ) && !visited[iii][jjj][0]){
                    visited[iii][jjj][0] = true;
                    queue.add(new Node(iii, jjj, 0));
                }
                if(iii>=0 && (arr[iii][jjj]==2 ) && !visited[iii][jjj][0]){
                    visited[iii][jjj][0] = true;
                }
                if(iii>=0 && (arr[iii][jjj]==3) && !visited[iii][jjj][3]){
                    visited[iii][jjj][3] = true;
                    queue.add(new Node(iii, jjj, 3));
                }
                if(iii>=0 && (arr[iii][jjj]==4 ) && !visited[iii][jjj][2]){
                    visited[iii][jjj][2] = true;
                    queue.add(new Node(iii, jjj, 2));

                }
            }
            // 하
            else if(dir == 1){
                int iii = ii+1;
                int jjj = jj;

                if(iii<N && (arr[iii][jjj]==0 || arr[iii][jjj]==9) && !visited[iii][jjj][1]){
                    visited[iii][jjj][1] = true;
                    queue.add(new Node(iii, jjj, 1));
                }
                if(iii<N && (arr[iii][jjj]==1) && !visited[iii][jjj][1]){
                    visited[iii][jjj][1] = true;
                    queue.add(new Node(iii, jjj, 1));
                }
                if(iii<N && (arr[iii][jjj]==2 ) && !visited[iii][jjj][1]){
                    visited[iii][jjj][1] = true;
                }
                if(iii<N && (arr[iii][jjj]==3 ) && !visited[iii][jjj][2]){
                    visited[iii][jjj][2] = true;
                    queue.add(new Node(iii, jjj, 2));
                }
                if(iii<N && (arr[iii][jjj]==4 ) && !visited[iii][jjj][3]){
                    visited[iii][jjj][3] = true;
                    queue.add(new Node(iii, jjj, 3));
                }
            }
            // 좌
            else if(dir == 2){
                int iii = ii;
                int jjj = jj-1;

                if(jjj>=0 && (arr[iii][jjj]==0 || arr[iii][jjj]==9) && !visited[iii][jjj][2]){
                    visited[iii][jjj][2] = true;
                    queue.add(new Node(iii, jjj, 2));
                }

                if(jjj>=0 && (arr[iii][jjj]==1 ) && !visited[iii][jjj][2]){
                    visited[iii][jjj][2] = true;
                }
                if(jjj>=0 && (arr[iii][jjj]==2 ) && !visited[iii][jjj][2]){
                    visited[iii][jjj][2] = true;
                    queue.add(new Node(iii, jjj, 2));
                }
                if(jjj>=0 && (arr[iii][jjj]==3 ) && !visited[iii][jjj][1]){
                    visited[iii][jjj][1] = true;
                    queue.add(new Node(iii, jjj, 1));
                }
                if(jjj>=0 && (arr[iii][jjj]==4 ) && !visited[iii][jjj][0]){
                    visited[iii][jjj][0] = true;
                    queue.add(new Node(iii, jjj, 0));
                }
            }
            //우
            else if(dir == 3){
                int iii = ii;
                int jjj = jj+1;

                if(jjj<M && (arr[iii][jjj]==0 || arr[iii][jjj]==9) && !visited[iii][jjj][3]){
                    visited[iii][jjj][3] = true;
                    queue.add(new Node(iii, jjj, 3));
                }
                if(jjj<M && (arr[iii][jjj]==1 ) && !visited[iii][jjj][3]){
                    visited[iii][jjj][3] = true;
                }
                if(jjj<M && (arr[iii][jjj]==2 ) && !visited[iii][jjj][3]){
                    visited[iii][jjj][3] = true;
                    queue.add(new Node(iii, jjj, 3));
                }
                if(jjj<M && (arr[iii][jjj]==3 ) && !visited[iii][jjj][0]){
                    visited[iii][jjj][0] = true;
                    queue.add(new Node(iii, jjj, 0));
                }
                if(jjj<M && (arr[iii][jjj]==4 ) && !visited[iii][jjj][1]){
                    visited[iii][jjj][1] = true;
                    queue.add(new Node(iii, jjj, 1));

                }
            }


        }
    }


}