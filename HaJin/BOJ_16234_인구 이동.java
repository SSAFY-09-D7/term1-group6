import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, L, R;
    public static int[][] arr;
    public static int day = 0;
    public static boolean[][] visited;
    public static boolean isPossible = false;

    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static List<Node> list;

    public static int sum = 0;

    public static class Node{
        int x;
        int y;
        int value;

        Node(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static void bfs(int i, int j){
        Queue<Node> queue = new LinkedList<>();
        list = new ArrayList<>();    // 국경선 열리는 나라 좌표들 저장

        list.add(new Node(i, j, arr[i][j]));
        queue.add(new Node(i, j, arr[i][j]));
        visited[i][j] = true;
        sum += arr[i][j];

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int xx = node.x;
            int yy = node.y;


            for(int p=0; p<4; p++){
                int xxx = xx + nr[p];
                int yyy = yy + nc[p];

                if(xxx>=0 && xxx<N && yyy>=0 && yyy<N && !visited[xxx][yyy]){
                    int sub = Math.abs(node.value - arr[xxx][yyy]);
                    if(sub>=L && sub<=R){
                        list.add(new Node(xxx, yyy, arr[xxx][yyy]));
                        queue.add(new Node(xxx, yyy, arr[xxx][yyy]));
                        sum += arr[xxx][yyy];
                        visited[xxx][yyy] = true;
                        isPossible = true;

                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            // 하루 시작
            visited = new boolean[N][N];
            // bfs 한 번이라도 일어나면 isPossible = true
            isPossible = false;
            // 전체 bfs 돌리기
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!visited[i][j]){
                        sum = 0;
                        bfs(i, j);

                        if(list.size() >1) {

                            for (int p = 0; p < list.size(); p++) {
                                arr[list.get(p).x][list.get(p).y] = sum / list.size();
                            }
                        }
                    }
                }
            }

            // bfs 끝나고 ispossible == false이면 반복문 멈추기
            if(isPossible)
                day += 1;
            else
                break;
        }

        System.out.println(day);

    }
}