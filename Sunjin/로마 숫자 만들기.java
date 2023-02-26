import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int sum;
    static int result;
    static boolean[] visited;
    static int[] value = {1, 5, 10, 50};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[1001];
        combR(0, 0);
        System.out.println(result);
    }

    private static void combR(int idx, int count) {
        if(count == N){
            if(!visited[sum]){
                visited[sum] = true;
                result++;
            }
            return;
        }
        for(int i = idx; i < 4; i++){
            sum += value[i];
            combR(i, count + 1);
            sum -= value[i];
        }
    }
}
