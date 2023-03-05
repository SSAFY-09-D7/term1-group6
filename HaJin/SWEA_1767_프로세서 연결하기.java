import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Solution {

    public static int T, N;
    public static int[][] arr;
    public static int MAXCNT;
    public static int MINLEN;
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
    public static List<Node> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++) {

            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            list = new ArrayList<>();
            MAXCNT = 0;
            MINLEN = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    // 코어 정보 저장
                    if(arr[i][j] == 1){
                        if(i==0 || i==N-1 || j==0 || j==N-1)
                            continue;
                        list.add(new Node(i, j));
                    }
                }
            }

            func(0, 0, 0);

            System.out.printf("#%d %d\n", test_case, MINLEN);
        }

    }

    // 리스트 인덱스, 최대 연결 개수, 최소 연결길이
    public static void func(int idx, int cnt, int len){
        

        if(idx == list.size()){
            if(cnt > MAXCNT){
                MAXCNT = cnt;
                MINLEN = len;
            }
            else if(cnt == MAXCNT){
                if(len < MINLEN)
                    MINLEN = len;
            }
            return;
        }

        int ii = list.get(idx).i;
        int jj = list.get(idx).j;

        for(int p=0; p<4; p++){

            int iii = ii;
            int jjj = jj;
            int tmplen = 0;
            boolean flag = false;

            while(true){
                iii += nr[p];
                jjj += nc[p];

                if(iii<0 || iii>=N || jjj<0 || jjj>=N){
                    flag = true;
                    break;
                }

                if(arr[iii][jjj] == 1){
                    tmplen = 0;
                    break;
                }
                tmplen += 1;
            }

            // 연결 성공한 경우
            if(flag){

                // 연결선 1로 바꾸기
                for(int q=1; q<=tmplen; q++){
                    arr[ii + (nr[p]*q)][jj + (nc[p]*q)] = 1;
                }

                func(idx+1, cnt+1, len+tmplen);

                // 연결선 다시 0으로
                for(int q=1; q<=tmplen; q++){
                    arr[ii + (nr[p]*q)][jj + (nc[p]*q)] = 0;
                }
            }
            // 연결 실패한 경우
            else{
                func(idx+1, cnt, len);
            }

        }

    }
}