import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int arr[][]= new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] distance = new int[N - 1];
        for(int i = 0; i < N - 1; i++){
            distance[i] = Math.abs(arr[i][0] - arr[i + 1][0]) + Math.abs(arr[i][1] - arr[i + 1][1]);
        }

        int[] jumpDistance = new int[N - 2];
        for(int i = 0; i < N - 2; i++){
            jumpDistance[i] = Math.abs(arr[i][0] - arr[i + 2][0]) + Math.abs(arr[i][1] - arr[i + 2][1]);
        }

        int max = 0;
        for(int i = 0; i < N - 2; i++){
            if(jumpDistance[i] < (distance[i] + distance[i + 1])) {
                int diff = (distance[i] + distance[i + 1]) - jumpDistance[i];
                if(max < diff){
                    max = diff;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < N - 1; i++){
            result += distance[i];
        }
        result -= max;
        System.out.println(result);
    }
}