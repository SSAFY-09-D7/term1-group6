import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int N, H, D, K;
    public static int now_x, now_y;

    public static char[][] arr;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};

    public static boolean[][][] visited;
    public static int MIN = -1;

    // 좌표, 좌표, 횟수, 현재 체력, 현재 우산 내구도, 현재 우산 번호
    public static class Node{
        int i;
        int j;
        int cnt;
        int life;
        int umblife;
        int umbNum;

        Node(int i, int j, int cnt, int life, int umblife, int umbNum){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.life = life;
            this.umblife = umblife;
            this.umbNum = umbNum;
        }
    }

    public static void func(int x, int y) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(x, y, 0, H, 0, 0));

        for(int i=0; i<K+1; i++){
            visited[x][y][i] = true;
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int xx = node.i;
            int yy = node.j;

            if(MIN != -1 && node.cnt > MIN){
                continue;
            }

            for(int i=0; i<4; i++){
                int xxx = xx + nr[i];
                int yyy = yy + nc[i];

                if(xxx>=0 && xxx<N && yyy>=0 && yyy<N){
                    if(arr[xxx][yyy] == '.'  && !visited[xxx][yyy][node.umbNum]){
                        if(node.umblife > 0){
                            queue.add(new Node(xxx, yyy, node.cnt+1, node.life, node.umblife-1, node.umbNum));
                            visited[xxx][yyy][node.umbNum] = true;
                        }
                        else if(node.life > 0){
                            queue.add(new Node(xxx, yyy, node.cnt+1, node.life-1, node.umblife, node.umbNum));
                            visited[xxx][yyy][node.umbNum] = true;
                        }
                    }
                    else if(arr[xxx][yyy] == 'E'  && !visited[xxx][yyy][node.umbNum]){
                        if(node.life > 0 || node.umblife>0){
                            if(MIN == -1)
                                MIN = node.cnt+1;
                            else if(MIN > node.cnt+1)
                                MIN = node.cnt+1;
                            return;
                        }

                    }
                    // 방문하지 않은 우산일 경우
                    else if(arr[xxx][yyy] >= '0' && arr[xxx][yyy]<='9' && !visited[xxx][yyy][(arr[xxx][yyy]-'0')+1]){
                        if(node.umblife > 0){
                            queue.add(new Node(xxx, yyy, node.cnt+1, node.life, D, arr[xxx][yyy] - '0'));
                            visited[xxx][yyy][(arr[xxx][yyy]-'0')+1] = true;
                        }
                        else if(node.life > 0){
                            queue.add(new Node(xxx, yyy, node.cnt+1, node.life-1, D, arr[xxx][yyy] - '0'));
                            visited[xxx][yyy][(arr[xxx][yyy]-'0')+1] = true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());	// 현재 체력
        D = Integer.parseInt(st.nextToken());	// 우산의 내구도
        K = 0;  // 우산 개수
        char umLabel = '0';

        arr = new char[N][N];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<N; j++) {
                arr[i][j] = s.charAt(j);
                if(arr[i][j] == 'S') {
                    now_x = i;
                    now_y = j;
                }
                else if(arr[i][j] == 'U') {
                    K += 1;
                    arr[i][j] = umLabel;
                    umLabel += 1;
                }
            }
        }

        visited = new boolean[N][N][K+1];

        func(now_x, now_y);
        System.out.println(MIN);
    }
}
