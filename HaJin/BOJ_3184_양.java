import java.io.*;
import java.util.*;

public class Main{
    public static int R, C;
    public static char[][] arr;
    public static boolean[][] visited;
    public static int tmpSheep, tmpWolf;
    public static int Sheep, Wolf;

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static class Node{
        int i;
        int j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void func(int i, int j){

        Queue<Node> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new Node(i, j));

        while(!queue.isEmpty()){
            Node node = queue.poll();

            if(arr[node.i][node.j] == 'v')
                tmpWolf += 1;
            else if(arr[node.i][node.j] == 'o')
                tmpSheep += 1;

            for(int p=0; p<4; p++){
                int ii = node.i + nr[p];
                int jj = node.j + nc[p];

                if(ii>=0 && ii<R && jj>=0 && jj<C && !visited[ii][jj] && arr[ii][jj]!='#'){
                    visited[ii][jj] = true;
                    queue.add(new Node(ii, jj));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                arr[i][j] = s.charAt(j);
            }
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[i][j] != '#' && !visited[i][j]){
                    tmpSheep = 0;
                    tmpWolf = 0;
                    func(i, j);

                    if(tmpSheep > tmpWolf)
                        Sheep += tmpSheep;
                    else
                        Wolf += tmpWolf;
                }
            }
        }

        System.out.println(Sheep+" "+Wolf);
    }
}
