import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 롤케이크의 길이
        int L = Integer.parseInt(br.readLine());
        // 방청객의 수
        int N = Integer.parseInt(br.readLine());

        int[] cake = new int[L];
        int beforeMax = 0;
        int afterMax = 0;

        int beforeResult = 0;
        int afterResult = 0;
        
        for(int i = 0; i < N; i++){
            int value = 0;
            st = new StringTokenizer(br.readLine());
            // 모두 다 0부터
            int P = Integer.parseInt(st.nextToken()) - 1;
            int K = Integer.parseInt(st.nextToken()) - 1;
            value = K - P;
            if(beforeMax < value) {
                beforeMax = value;
                beforeResult = i + 1;
            }

            int count = 0;
            for(int j = P; j <= K; j++){
                if(cake[j] != 0){
                    continue;
                }
                cake[j] = i + 1;
                count++;
            }
            if(afterMax < count)  {
                afterMax = count;
                afterResult = i + 1;
            }
        }
        System.out.println(beforeResult);
        System.out.println(afterResult);
    }
}
