//추가문제 - 문제21 소금쟁이 중첩
//구현 - 2차원배열

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WaterStrider01 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T ; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());    //연못 크기
            int m = Integer.parseInt(st.nextToken());    //소금쟁이 수

            boolean[][] map = new boolean[n][n];
            int ans=0;

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());    //위치 r
                int c = Integer.parseInt(st.nextToken());    //위치 c
                int d = Integer.parseInt(st.nextToken());    //방향. 하=1 우=2

                map[r][c] = true;

                //d[i]가 1 or 2 로 나누어서 각각 처리 -> switch
                //뛸 때 가장자리 조건
                //뛸때마다 영역에 표시 -> true
                //소금쟁이 뛴 자리가 true -> 그 때 소금쟁이 번호를 ans로 출력 후 break
                //for문을 3-2-1 돌리면서 위치좌표를 줄여나간다
                for(int j=3;j>0;j--) {
                    switch (d) {
                        case 1: r += j; break;
                        case 2: c += j; break;
                    }

                    if(r >= n || c >= n) break;     //가장자리 조건

                    if(!map[r][c]) {
                        map[r][c] = true;
                    } else {
                        ans = i;
                        break;
                    }
                }
            }
            System.out.println("#" + t + " " + ans);
        }
    }
}
