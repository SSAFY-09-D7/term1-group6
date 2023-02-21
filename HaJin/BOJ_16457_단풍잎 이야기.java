import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    public static int n, m, k;
    public static int[] ans;
    public static int[][] skill;
    public static int maxCnt = 0;

    public static void func(int idx, int kk){
        if(kk == n){

            boolean[] visited = new boolean[2*n +1];
            for(int a : ans)
                visited[a] = true;
           
            int cnt = 0;
            for(int i=0; i<m; i++){
                int tmpCnt = 0;
                for(int j=0; j<k; j++){
                    //System.out.println(skill[i][j]);
                    if(visited[skill[i][j]])
                        tmpCnt += 1;
                }
                if(tmpCnt == k)
                    cnt += 1;
            }

            if(cnt > maxCnt){
                maxCnt = cnt;
            }
            return;
        }

        for(int i=idx; i<=2*n; i++){
            ans[kk] = i;
            func(i+1, kk+1);
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ans = new int[n];
        skill = new int[m][k];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<k; j++){
                skill[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(1, 0);

        System.out.println(maxCnt);

    }
}
