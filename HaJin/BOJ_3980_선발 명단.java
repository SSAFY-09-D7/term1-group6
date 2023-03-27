import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int[][] arr;
    public static boolean[] visited;
    public static int MAX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < C; test_case++){

            MAX = 0;
            arr = new int[11][11];
            visited = new boolean[11];

            for(int i=0; i<11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            func(0, 0);
            sb.append(MAX).append("\n");
        }
        System.out.println(sb);
    }

    public static void func(int cnt, int sum){
        if(cnt == 11){
            if(MAX < sum)
                MAX = sum;
            return;
        }

        for(int i=0; i<11; i++){
            if(!visited[i] && arr[cnt][i] != 0){
                visited[i] = true;
                func(cnt+1, sum + arr[cnt][i]);
                visited[i] = false;
            }
        }
    }

}