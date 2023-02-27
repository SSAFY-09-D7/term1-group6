import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static int N;
    public static int[] arr = {1, 5, 10, 50};
    public static boolean[] ansList = new boolean[1001];
    public static int[] ans;

    public static void func(int idx, int k){

        if(k == N){
            int sum = 0;
            for(int i=0; i<N; i++){
                sum += ans[i];
            }
            ansList[sum] = true;
            return;
        }

        for(int i=idx; i<4; i++){
            ans[k] = arr[i];
            func(i,k+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ans = new int[N];

        func(0, 0);

        int cnt = 0;
        for(int i=0; i<1001; i++){
            if(ansList[i])
                cnt+=1;
        }

        System.out.println(cnt);
    }
}
