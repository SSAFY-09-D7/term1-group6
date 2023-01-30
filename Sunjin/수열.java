import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cs_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 최댓값
        int max = K * -100;

        // 시작점
        for(int i = 0; i < N - K + 1; i++){
            int sum = 0;
            // K개의 합
            for(int j = i; j < i + K; j++){
                sum += arr[j];
            }
            if(max < sum) max = sum;
        }

        System.out.println(max);
    }
}
