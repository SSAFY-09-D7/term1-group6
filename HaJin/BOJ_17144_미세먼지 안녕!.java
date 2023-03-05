import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int R, C, T;
    public static int[][] arr;
    public static int[][] newArr;
    public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int airR1, airC1;

    public static int[] antiR = {0, 1, 0, -1};
    public static int[] antiC = {1, 0, -1, 0};
    public static int[] clockR = {1, 0, -1, 0};
    public static int[] clockC = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        boolean first = false;
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(!first && arr[i][j] == -1){
                    airR1 = i;
                    airC1 = j;
                    first = true;
                }

            }
        }

        int cnt = 1;
        while(cnt <= T){

            // 미세먼지 확산
            newArr = new int[R][C];

            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(arr[i][j] != 0 && arr[i][j] != -1){
                        diffusion(i, j);
                    }
                }
            }


            // 공기청정기 작동 (반시계)
            antiClock();

            // 공기청정기 부분 0으로
            newArr[airR1][airC1] = 0;

            // 공기청정기 작동 (시계)
            Clock();
            newArr[airR1+1][airC1] = 0;

            // arr에 newArr 복사하기
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    arr[i][j] = newArr[i][j];
                }
            }

            arr[airR1][airC1] = -1;
            arr[airR1+1][airC1] = -1;

            cnt += 1;
        }


//        for(int i=0; i<R; i++){
//            for(int j=0; j<C; j++)
//                System.out.print(newArr[i][j] + " ");
//            System.out.println();
//        }
        int sum = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[i][j] != -1)
                    sum += arr[i][j];
            }
        }

        System.out.println(sum);

    }

    public static void diffusion(int i, int j){

        int cnt = 0;    // 몇개의 방향으로 확산
        boolean[] checkP = new boolean[4];
        for(int p=0; p<4; p++){
            int ii = i + nr[p];
            int jj = j + nc[p];

            if(ii>=0 && ii<R && jj>=0 && jj<C && arr[ii][jj]!=-1){
                cnt+=1;
                checkP[p] = true;
            }
        }

        newArr[i][j] += arr[i][j] - (arr[i][j]/5) * cnt;
        for(int k=0; k<4; k++){
            if(checkP[k]){
                newArr[i+nr[k]][j+nc[k]] += arr[i][j]/5;
            }
        }
    }

    public static void antiClock(){
        int x = 0;
        int y = 0;
        int tmp = newArr[x][y]; // 시작점
        int idx = 0;

        while(idx < 4){
            int xx = x + antiR[idx];
            int yy = y + antiC[idx];

            // 범위 안에 들어오면
            if(xx <= airR1 && xx>=0 && yy>=0 && yy<C){
                newArr[x][y] = newArr[xx][yy];
                x = xx;
                y = yy;
            }
            else{
                idx += 1;
            }
        }
        newArr[1][0] = tmp;
    }

    public static void Clock(){
        int x = airR1 + 1;
        int y = airC1;
        int tmp = newArr[x][y]; // 시작점
        int idx = 0;

        while(idx < 4){
            int xx = x + clockR[idx];
            int yy = y + clockC[idx];

            if(xx >= airR1+1 && xx<R && yy>=0 && yy<C){
                newArr[x][y] = newArr[xx][yy];
                x = xx;
                y= yy;
            }
            else{
                idx += 1;
            }
        }
        newArr[airR1+1][airC1+1] = tmp;
    }

}