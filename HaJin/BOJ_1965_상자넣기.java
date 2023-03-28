import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static int[] arr;
    public static int[] dp;     // 현재까지 증가한 박스 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 1로 초기화
        for(int i=0; i<N; i++) {
            dp[i] = 1;
        }

        for(int i=0; i<N; i++){
            // 뒤 수들 중 큰수 찾기
            for(int j=i+1; j<N; j++){
                if(arr[i] < arr[j])
                    dp[j] = Math.max(dp[j], dp[i]+1);
            }
        }

        // 최댓값 찾기
        int ans = 1;
        for(int i=0; i<N; i++){
            if(dp[i] > ans)
                ans = dp[i];
        }

        System.out.println(ans);
    }
}