import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[2000005];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            arr[value] = 1;
        }

        int x = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i = 1; i < x; i++){
            // 존재하는 수
            if(arr[i] == 1 && arr[x - i] == 1) count++;
        }

        System.out.println(count / 2);
    }
}
