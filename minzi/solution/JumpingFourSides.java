//추가문제 - 32. 점프 사방
//구현 - 2차원배열

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpingFourSides {

    public static int[] dr = {0,1,0,-1};  //동 남 서 북
    public static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T ; t++) {
            int sum=0;
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());    //행
            int c = Integer.parseInt(st.nextToken());    //열
            int player = Integer.parseInt(st.nextToken());  //참가자 수
            int[][] map = new int[r][c];
            int[] playerR = new int[player];
            int[] playerC = new int[player];
            int[] playerChance = new int[player];

            //맵에 숫자 채우기
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < player; i++) {
                st = new StringTokenizer(br.readLine());
                playerR[i] = Integer.parseInt(st.nextToken());    //참가자 행 넘버
                playerC[i] = Integer.parseInt(st.nextToken());    //참가자 열 넘버
                playerChance[i] = Integer.parseInt(st.nextToken());  //참가자 참여 횟수
                playerR[i]--;  //맵은 1부터 시작하므로 1씩 줄인다
                playerC[i]--;
            }
            //함정 표시
            st = new StringTokenizer(br.readLine());
            int trapNum = Integer.parseInt(st.nextToken());
            for (int i = 0; i < trapNum; i++) {
                int trapR = Integer.parseInt(st.nextToken());
                int trapC = Integer.parseInt(st.nextToken());
                map[trapR-1][trapC-1] = 0;
            }
            //플레이어 수만큼 반복, 각 플레이어의 참여횟수만큼 반복
            for (int i = 0; i < player; i++) {
                int num;
                for (int j = 0; j < playerChance[i]; j++) {
                    num = map[playerR[i]][playerC[i]];  //플레이어 위치에 적힌 숫자
                    //10으로 나눈 몫을 가지고(십의자리) 동/남/서/북 정하기
                    int direction = num / 10;
                    //10으로 나눈 나머지를 가지고(일의자리) 그 방향으로 나머지 수만큼 이동
                    int size = num % 10;
                    //플레이어가 지정한 자리로 이동
                    playerR[i]+=(dr[direction-1]*size);
                    playerC[i]+=(dc[direction-1]*size);

                    //가장자리를 벗어나거나 || 함정을 만나면 sum-=1000하고 break
                    //이때 가장자리 조건을 먼저 안써주면 arrayIndexOutofBoundsException 에러남. 순서 중요
                    if(playerR[i] < 0 || playerR[i] >= r || playerC[i] < 0 || playerC[i] >= c || map[playerR[i]][playerC[i]] == 0 ) {
                        sum-=1000;
                        break;
                    }
                    // 현재 플레이어가 마지막 횟수까지 제대로 왔을 때 상금 계산 -> 다음 플레이어로 다시 반복
                    if(j == playerChance[i]-1) {
                        num = map[playerR[i]][playerC[i]];
                        sum += ((-1000)+num*100);
                    }
                }
            }
            System.out.println("#" + t + " " + sum);
        }
    }
}
