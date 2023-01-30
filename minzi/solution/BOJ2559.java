
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());      //온도 측정한 전체 날짜 수
        int k = Integer.parseInt(st.nextToken());      //합을 위한 연속적인 날짜의 수

        st = new StringTokenizer(br.readLine());
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = Integer.parseInt(st.nextToken());
        }
        int sum;
        int max = -10000000;  // = -10^5 *10^2
        for (int i = 0; i < n - k + 1; i++) {   //온도의 합 개수 반복
            sum = 0;
            //
            for (int j = i; j < k + i; j++) {
                sum += tmp[j];
            }
            if (max <= sum)
                max = sum;
        }
        System.out.println(max);
    }
}
