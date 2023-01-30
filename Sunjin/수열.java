import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        // 최댓값
        int sum = 0;

        for(int i = 0; i < K; i++){
            sum += array[i];
        }
        int max = sum;

        for(int i = K; i < N; i++){
            sum -= array[i - K];
            sum += array[i];
            if(max <= sum){
                max = sum;
            }
        }
        System.out.println(max);
    }
}
