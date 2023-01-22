// 추가문제 - 문제11 로봇 이동거리
// 구현 - 2차원배열

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class RobotMoving {

    static int T;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            int cnt = 0;
            int n = Integer.parseInt(br.readLine());
            char[][] map = new char[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = st.nextToken().charAt(0);
                }
            }
            //배열을 돌면서 A,B,C를 찾는다 -> 찾으면 각각 움직일 수 있는 거리로 S일때만 이동 가능하게 함.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    switch (map[i][j]) {
                        case 'A':
                            //우
                            int ncA = j + dc[3];
                            if (ncA < 0 || ncA >= n) continue;    //가장자리 조건. switch문 탈출
                            while (map[i][ncA] == 'S') {
                                ncA++;
                                cnt++;
                                if (ncA >= n) break;    //가장자리 조건. while문 탈출
                            }
                            break;
                        case 'B':
                            //좌, 우
                            for (int k = 2; k <= 3; k++) {
                                int ncB = j + dc[k];
                                if (ncB < 0 || ncB >= n) continue;
                                while (map[i][ncB] == 'S') {
                                    if (k == 2) ncB--;
                                    if (k == 3) ncB++;
                                    cnt++;
                                    if (ncB < 0 || ncB >= n) break;
                                }
                            }
                            break;
                        case 'C':
                            //상 하 좌 우
                            for (int k = 0; k < 4; k++) {
                                int nrC = i + dr[k];
                                int ncC = j + dc[k];
                                if (nrC < 0 || nrC >= n || ncC < 0 || ncC >= n) continue;
                                while (map[nrC][ncC] == 'S') {
                                    switch (k) {
                                        case 0:
                                            nrC--;
                                            break;
                                        case 1:
                                            nrC++;
                                            break;
                                        case 2:
                                            ncC--;
                                            break;
                                        case 3:
                                            ncC++;
                                            break;
                                    }
                                    cnt++;
                                    if (nrC < 0 || nrC >= n || ncC < 0 || ncC >= n) break;
                                }
                            }
                            break;
                    }
                }
            }
            System.out.println("#" + t + " " + cnt);
        }
    }
}
