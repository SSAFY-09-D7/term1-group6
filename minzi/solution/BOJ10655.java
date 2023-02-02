import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        int[] y = new int[n];
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

            //배열 d1에 좌표 사이의 거리를 넣고 sum에 총 거리를 넣는다
        for (int i = 0; i < n-1; i++) {
            d1[i] = Math.abs(x[i+1]-x[i]) + Math.abs(y[i+1]-y[i]);
            sum += d1[i];
        }
            //하나 건너뛴 거리들을 d2에 저장
        for (int i = 0; i < n-2; i++) {
            d2[i] = Math.abs(x[i+2]-x[i]) + Math.abs(y[i+2]-y[i]);
        }

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < n-2; i++) {
            //총 거리에서 체크포인트를 지나야 하는 거리 2개를 빼고 체크포인트 앞뒤로 좌표 사이의 거리를 더해 계산하고, 최솟값이랑 비교
            int tmp = sum + d2[i] - d1[i] - d1[i+1];
            min = Math.min(tmp, min);
        }
        System.out.println(min);
    }

}
