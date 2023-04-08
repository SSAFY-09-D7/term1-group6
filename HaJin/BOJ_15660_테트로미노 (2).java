import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr;
    public static int[] nr = {1, -1, 0, 0};
    public static int[] nc = {0, 0, 1, -1};
    public static boolean[][] visited;
    public static int ANS = Integer.MIN_VALUE;
    public static int maxArr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(maxArr < arr[i][j])
                    maxArr = arr[i][j];
            }
        }

        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited[i][j] = true;
                func(i, j, 1, arr[i][j], true);
                visited[i][j] = false;
            }
        }

        System.out.println(ANS);
    }

    public static void func(int i, int j, int cnt, int sum, boolean isFirst){

        // 최대값으로만 더했을때보다 ANS 크면 return
        if(isFirst){
            if(ANS > sum + (8-cnt)*maxArr)
                return;
        }
        else{
            if(ANS > sum + (4-cnt)*maxArr)
                return;
        }

        if(cnt == 4){

            // 첫번째 테트로미노인 경우 두번째 테트로미노 시작
            if(isFirst) {
                for(int p=i; p<N; p++) {
                    for(int q=0; q<M; q++) {
                        if(!visited[p][q]) {
                            visited[p][q] = true;
                            func(p, q, 1, sum + arr[p][q], false);
                            visited[p][q] = false;
                        }
                    }
                }
            }
            // 두번째 테트로미노인 경우 최댓값 비교
            else {
                if(sum > ANS)
                    ANS = sum;
            }

            return;
        }

        for(int p=0; p<4; p++){
            int ii = i + nr[p];
            int jj = j + nc[p];

            if(ii>=0 && ii<N && jj>=0 && jj<M && !visited[ii][jj]){

                if(cnt == 2){
                    visited[ii][jj] = true;
                    func(i, j, cnt+1, sum+arr[ii][jj], isFirst);
                    visited[ii][jj] = false;
                }

                visited[ii][jj] = true;
                func(ii, jj, cnt+1, sum+arr[ii][jj], isFirst);
                visited[ii][jj] = false;
            }
        }

    }
}