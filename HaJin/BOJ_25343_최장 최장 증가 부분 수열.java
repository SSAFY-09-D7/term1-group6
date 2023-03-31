import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static int[][] arr;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){

                if(i==0 && j==0){
                    dp[i][j] = 1;
                    continue;
                }

                // (0, 0) ~ (i, j) 범위에서 자기 자신보다 작은 값들 중 dp 최댓값 갱신
                int max = 0;

                for(int p=0; p<=i; p++){
                    for(int q=0; q<=j; q++){
                        if(arr[i][j] > arr[p][q] && dp[p][q] > max)
                            max = dp[p][q];
                    }
                }

                // dp[i][j] 갱신
                dp[i][j] = Math.max(dp[i][j], max+1);
            }
        }

        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(ans < dp[i][j])
                    ans = dp[i][j];
            }
        }

        System.out.println(ans);

    }
}