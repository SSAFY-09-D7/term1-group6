//추가문제 - 문제22 소금쟁이 합계
//구현 - 2차원배열

//import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WaterStrider02 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int ans=0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());  //연못크기
            int m = Integer.parseInt(st.nextToken());  //소금쟁이 수
            boolean[][] map = new boolean[n][n];

            for(int i=1; i <=m; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                //세번째 갔을 때 True
                //true 인 곳에 가면 -> break
                for (int k = 3; k > 0 ; k--) {
                    switch(d) {
                        case 1: r-=k; break;
                        case 2: r+=k; break;
                        case 3: c-=k; break;
                        case 4: c+=k; break;
                    }
                    //가장자리 조건
                    if(r<0 || c<0 || r>=n || c >= n) break;
                    //좌표가 false면 계속 감. true - break
                    if(!map[r][c]) {
                        if(k == 1) {
                            map[r][c] = true;
                            ans++;
                        }
                    }
                    else break;
                }
            }
            System.out.println("#" + t + " " + ans);
        }

    }
}
