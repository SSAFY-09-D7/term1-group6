import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            // 입력 시작
            st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[Y + 1][X + 1];

            for(int i = 1; i <= Y; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= X; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] info = new int[N][3];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                info[i][0] = Integer.parseInt(st.nextToken());
                info[i][1] = Integer.parseInt(st.nextToken());
                info[i][2] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            int obsCount = Integer.parseInt(st.nextToken());
            for(int i = 0; i < obsCount; i++) {
                int obsRow = Integer.parseInt(st.nextToken());
                int obsCol = Integer.parseInt(st.nextToken());
                map[obsRow][obsCol] = 0;
            }
            // 입력 종료

            int price = 0;

            // 구현 시작
            for(int i = 0; i < N; i++) {
                int curY = info[i][0];
                int curX = info[i][1];
                int jump = info[i][2];
                boolean exit = false;

                for(int j = 0; j < jump; j++) {
                    int value = map[curY][curX];
                    int dir = value / 10;
                    int distance = value % 10;

                    // 함정에 빠질 경우 움직이지 못함
                    if (dir == 1) {
                        curX += distance;
                    } else if (dir == 2) {
                        curY += distance;
                    } else if (dir == 3) {
                        curX -= distance;
                    } else if (dir == 4) {
                        curY -= distance;
                    }
                    
                    // 지도를 벗어날 경우
                    if (curX < 1 || curX > X || curY < 1 || curY > Y) {
                        price -= 1000;
                        exit = true;
                        break;
                    }
                }
                if(!exit){
                    int money = map[curY][curX] * 100;
                    money -= 1000;
                    price += money;
                }
            }
            System.out.println("#" + test_case + " " + price);
        }
    }
}