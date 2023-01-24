//추가문제 - 문제42. 미로 도착지점
//구현 - 2차원배열

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MazeArrival {

    public static int[] dr = {-1,0,1,0};  //북 동 남 서
    public static int[] dc = {0,1,0,-1};  //북 동 남 서

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int jumper = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[n][n];

            //점퍼 좌표 받으면 map에 true로 표시
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < jumper; i++) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r-1][c-1] = true;
            }
            //이동 지시
            st = new StringTokenizer(br.readLine());
            int movingNum = Integer.parseInt(st.nextToken());

            int nr = startR;
            int nc = startC;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < movingNum; i++) {

                int direction = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt((st.nextToken()));

                nr += dr[direction-1]*size;
                nc += dc[direction-1]*size;
                //가장자리 조건에서 벗어나거나 점퍼를 만나면 break
                if( nr<0 || nc<0 || nc>=n  || nr>=n || map[nr][nc] ) {
                    nr=0;
                    nc=0;
                    break;
                }
            }
            System.out.println("#" + t + " " + nr + " "+ nc);

        }

    }

}
