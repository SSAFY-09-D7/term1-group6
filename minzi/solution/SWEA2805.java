//[SWEA] 2805. 농작물 수확하기
//구현 - 2차원배열


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2805 {
    static int sumCol(int center, int sum, int i, int[][]map, int r, int n) {
        for (int j = center-i; j > 0 ; j--) {
            int c;
            c = j-1;
            sum-=map[r][c];
            c = n-j;
            sum-=map[r][c];
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] map = new int[n][n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();

                for (int j = 0; j < n; j++) {
                    map[i][j] = s.charAt(j) - '0';
                    sum+=map[i][j];
                }
            }
            //배열의 한 가운데를 찾고 - n/2 n/2
            int center = n/2;
            int r;


            //sum에서 4분면을 하나씩 잡고 빼간다   -> 이부분을 더 좋은 코드로 만들고 싶은데 어떻게 해야될 지 모르겠음
            for (int i = 0; i < center ; i++) {
                r = i;
                sum = sumCol(center,sum, i, map, r, n);
                r = (n-1)-i;
                sum = sumCol(center,sum, i, map, r, n);
            }
            System.out.println("#" + t + " " + sum);
        }
    }
}