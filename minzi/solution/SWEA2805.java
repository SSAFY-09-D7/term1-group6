//[SWEA] 2805. 농작물 수확하기
//구현 - 2차원배열


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }
            //배열의 한 가운데를 찾고 - n/2 n/2
            int center = n/2;

            for (int i = 0; i < n; i++) {  //행
                if(i <= center) {
                    for(int j = center-i; j <= center+i ; j++)   //열
                        sum += map[i][j];
                } else {
                    for(int j = i-center; j <= center+n-1-i; j++)
                        sum += map[i][j];
                }
            }
            System.out.println("#" + t + " " + sum);
        }
    }
}