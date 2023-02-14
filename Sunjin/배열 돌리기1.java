import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int boxCount = Math.min(N, M) / 2;
        int[] dRow = {0, 1, 0 ,-1};
        int[] dCol = {1, 0, -1, 0};
        for(int rotate = 0; rotate < R; rotate++){
            for(int start = 0; start < boxCount; start++){
                int dir = 0;
                int curRow = start;
                int curCol = start;
                int startValue = map[curRow][curCol];
                while(dir < 4){
                    int nextRow = curRow + dRow[dir];
                    int nextCol = curCol + dCol[dir];

                    if(nextRow < start || nextCol < start || nextRow >= N - start || nextCol >= M - start) {
                        dir++;
                    }
                    else{
                        map[curRow][curCol] = map[nextRow][nextCol];
                        curRow = nextRow;
                        curCol = nextCol;
                    }
                }
                map[start + 1][start] = startValue;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
