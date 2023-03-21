import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int R, C;
    public static char[][] arr;

    public static int[] nr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    public static int[] nc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static int[] Cnr = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] Cnc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static int JongSuMoveCnt = 0;
    public static int JongSuX, JongSuY;
    public static int[] JongSu;        // 종수 이동 순서 저장
    public static boolean flag = true;
    public static int[][] CrazyCnt;    // 해당 칸에 미친 아두이노 몇 개 있는지 저장

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        CrazyCnt = new int[R][C];

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                arr[i][j] = s.charAt(j);

                if(arr[i][j] == 'I'){
                    JongSuX = i;
                    JongSuY = j;
                }
                else if(arr[i][j] == 'R'){
                    CrazyCnt[i][j] = 1;
                }
            }
        }

        String s = br.readLine();
        JongSu = new int[s.length()];
        for(int i=0; i<s.length(); i++){
            JongSu[i] = s.charAt(i) - '0';
        }

        int newX = 0;
        int newY = 0;
        while(JongSuMoveCnt < s.length()){

            // 1. 종수 이동
            newX = JongSuX + nr[JongSu[JongSuMoveCnt]];
            newY = JongSuY + nc[JongSu[JongSuMoveCnt]];

            JongSuMoveCnt += 1;

            // 2. 해당 칸에 미친 아두이노 있는지 확인
            if(arr[newX][newY] == 'R'){
                break;
            }
            else{
                arr[JongSuX][JongSuY] = '.';
                arr[newX][newY] = 'I';

                JongSuX = newX;
                JongSuY = newY;
            }

            // 3. 미친 아두이노 이동
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(arr[i][j] == 'R'){
                        CrazyAMove(i, j);
                    }
                }
            }

            // 4. 미친 아두이노가 종수의 아두이노 칸으로 이동하면 게임 종료
            if(!flag){
                break;
            }

            // 5. 2개 이상 같은 칸에 있으면 폭발
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(CrazyCnt[i][j] > 1){
                        CrazyCnt[i][j] = 0;
                    }
                }
            }

            // 기존 미친아두이노 지우고 미친아두이노 새로운 칸에 표시
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(arr[i][j] == 'R'){
                        arr[i][j] = '.';
                    }
                    if(CrazyCnt[i][j] == 1){
                        arr[i][j] = 'R';
                    }
                }
            }
        }

        if(JongSuMoveCnt == s.length()){
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
        else{
            System.out.printf("kraj %d\n", JongSuMoveCnt);
        }

    }

    public static void CrazyAMove(int i, int j){

        int minDist = Integer.MAX_VALUE;
        int minDistIdx = 0;

        int newX = 0;
        int newY = 0;

        for(int p=0; p<8; p++){
            newX = i + Cnr[p];
            newY = j + Cnc[p];

            if(newX>=0 && newX<R && newY>=0 && newY<C){
                int dist = Math.abs(JongSuX - newX) + Math.abs(JongSuY - newY);
                if(minDist > dist){
                    minDist = dist;
                    minDistIdx = p;
                }
            }
        }

        newX = i + Cnr[minDistIdx];
        newY = j + Cnc[minDistIdx];

        // 4. 미친 아두이노가 종수의 아두이노 칸으로 이동하면 게임 종료
        if(minDist == 0){
            flag = false;
            return;
        }

        // 5. 2개 이상 같은 칸에 있으면 폭발
        CrazyCnt[i][j] -= 1;
        CrazyCnt[newX][newY] += 1;

    }

}