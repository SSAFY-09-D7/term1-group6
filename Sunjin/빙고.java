import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N = 5;
    static int[][] map = new int[N][N];
    static boolean[][] check = new boolean[N][N];
    static int bingoCount;
    static int result;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        flag = false;
        int count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                count++;
                if (!flag) {
                    draw(value);
                    bingoCount = 0;
                    check(count);
                }
            }
        }
        System.out.println(result);
    }

    private static void check(int count) {
        checkRow();
        checkCol();
        checkRSlash();
        checkLSlash();
        if(bingoCount >= 3){
            flag = true;
            result = count;
        }
    }

    // 0,4  1,3  2,2  3,1  4,0
    private static void checkLSlash() {
        int count = 0;
        for(int num = 0; num < N; num++){
            int row = num;
            int col = N - num - 1;
            if(check[row][col]) count++;
            else break;
        }
        if(count == N) bingoCount++;
    }

    // 0,0  1,1  2,2, 3,3  4,4
    private static void checkRSlash() {
        int count = 0;
        for(int num = 0; num < N; num++){
            if(check[num][num]) count++;
            else break;
        }
        if(count == N) bingoCount++;
    }

    private static void checkCol() {
        for(int i = 0; i < N; i++){
            int count = 0;
            for(int j = 0; j < N; j++){
                if(!check[i][j]) break;
                count++;
            }
            if(count == N) bingoCount++;
        }
    }

    private static void checkRow() {
        for(int j = 0; j < N; j++){
            int count = 0;
            for(int i = 0; i < N; i++){
                if(!check[i][j]) break;
                count++;
            }
            if(count == N) bingoCount++;
        }
    }

    private static void draw(int value) {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == value) check[i][j] = true;
            }
        }
    }
}
