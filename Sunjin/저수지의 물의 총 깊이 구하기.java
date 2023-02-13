import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int max;
    static int value;
    static char[][] map;
    static int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            max = 0;
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = st.nextToken().charAt(0);
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] == 'G') continue;
                    func(i, j);
                }
            }
            if(max == 0) max = 1;
            sb.append("#" + testCase + " " + max + "\n");
        }
        System.out.println(sb);
    }

    private static void func(int row, int col) {
        value = 0;
        for(int i = 0; i < 8; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];
            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N){
                continue;
            }
            if(map[nextRow][nextCol] == 'W') value++;
        }
        if(max < value) max = value;
    }
}
