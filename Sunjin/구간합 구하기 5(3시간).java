import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for(int row = 1; row <= N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 1; col <= N; col++){
                map[row][col] = map[row][col -1] + map[row - 1][col] - map[row - 1][col - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = solve(x1, y1, x2, y2);
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }

    private static int solve(int x1, int y1, int x2, int y2) {
        int result = 0;
        // 큰 사각형 더하기
        result += map[x2][y2];
        // 왼쪽 사각형 빼기
        result -= map[x2][y1 - 1];
        // 오른쪽 사각형 빼기
        result -= map[x1 - 1][y2];
        // 작은 사각형 더하기
        result += map[x1 - 1][y1 - 1];

        return result;
    }
}
