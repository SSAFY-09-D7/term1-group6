import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static int[] sour;
    public static int[] bitter;

    public static int[] ans;

    public static int sub = 1000000000;

    // idx : 원 배열
    // k : ans index
    // m : 재료 몇 개 고를지
    public static void func(int idx, int k, int m){
        if(k == m){
            //for(int i=0; i<m; i++)
                //System.out.print(ans[i] + " ");
            //System.out.println(Arrays.toString(ans));

            int ssour = 1;
            int bbiter = 0;
            for(int i=0; i<m; i++){
                ssour *= sour[ans[i]];
                bbiter += bitter[ans[i]];
            }
            int tmp_sub;
            if(ssour > bbiter)
                tmp_sub = ssour - bbiter;
            else tmp_sub = bbiter - ssour;

            if(tmp_sub < sub)
                sub = tmp_sub;

            return;


        }

        for(int i=idx; i<N; i++){
            ans[k] = i;
            func(i+1, k+1, m);
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        sour = new int[N];
        bitter = new int[N];
        ans = new int[10];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        // 재료 1개 고르기 ~ 재료 N개 고르기
        for(int i=1; i<=N; i++){
            func(0,0, i);
        }

        System.out.println(sub);
    }
}

