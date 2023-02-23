// BOJ 1987 알파벳
import java.io.*;
import java.util.*;

public class BAEK_1987 {
	// 방문한 알파벳에 대한 정보 저장
	static boolean[] alphaMap = new boolean[26];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int R, C, ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());  // 세로 (행)
        C = Integer.parseInt(st.nextToken());  // 가로 (열)
        
        
        // 각 알파벳에 해당하는 수(int)를 배열에 담아 저장한다.
        // (방문 배열 alphaMap에 적용하기 위함)
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				char t = tmp.charAt(j);
				map[i][j] = t - 65;
			}
		}
//        
//        for (int[] sts : map) {
//			System.out.println(Arrays.toString(sts));
//		}
 
        ret = 0;
        check(0, 0, 1);
        System.out.println(ret);

	}

	// row, col : 현재 위치
	//      cnt : 이동 횟수
	private static void check(int row, int col, int cnt) {
		alphaMap[map[row][col]] = true;
		
		for (int idx = 0; idx < 4; idx++) {
			int nx = row + dx[idx];
			int ny = col + dy[idx];
			
			if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
			
			// 아직 방문하지 않은 알파벳 종류라면 이동
			if (!alphaMap[map[nx][ny]]) {
				check(nx, ny, cnt+1);
			}
			
		}
		
		// for문을 나왔다 = 경우를 모두 탐색했다 -> 이동 횟수를 반영
		// 호출되었던 곳으로 돌아가면 다른 경우에 대해서도 탐색할 수 있도록 false로 변경
		if (cnt > ret) ret = cnt;
		alphaMap[map[row][col]] = false;
	}
}
