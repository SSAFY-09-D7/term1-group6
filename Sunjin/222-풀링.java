import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = pooling(0, 0, N);
        System.out.println(result);
    }

    private static int pooling(int row, int col, int size) {

        if(size == 2){
            int[] tmp = new int[4];
            int idx = 0;
            for(int i = row; i < row + 2; i++){
                for(int j = col; j < col + 2; j++){
                    tmp[idx] = map[i][j];
                    idx++;
                }
            }
            Arrays.sort(tmp);
            return tmp[2];
        }

        int[] arr = new int[4];
        int half = size / 2;

        arr[0] = pooling(row, col, half);
        arr[1] = pooling(row, col + half, half);
        arr[2] = pooling(row + half, col, half);
        arr[3] = pooling(row + half, col + half, half);

        Arrays.sort(arr);
        return arr[2];
    }
}
