import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            for(int i = 0; i < N; i++){
                String str = br.readLine();
                int[] line = new int[N];
                for(int j = 0; j < N; j++){
                    line[j] = str.charAt(j) - '0';
                }
                map[i] = line;
            }
            
            // 입력 확인
//            for(int i = 0; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
            int result = 0;

            for(int i = 0; i <= N / 2; i++){
                for(int j = N / 2 - i; j <= N / 2 + i; j++){
                    result += map[i][j];
                }
            }

            for(int i = N / 2 + 1; i < N; i++){
                for(int j = N / 2 - (N - i) + 1; j < N / 2 + (N - i); j++){
                    result += map[i][j];
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}
