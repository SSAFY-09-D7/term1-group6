// SWEA 1949 등산로 조성
import java.io.*;
import java.util.*;
public class SWEA_1949
{
	static int[][] map;
	static int N, K, depth, maxV, ret;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // NxN
			K = Integer.parseInt(st.nextToken());  // 공사 가능 깊이
			
			map = new int[N][N];        // 등산 맵
			visit = new boolean[N][N];  // 경로(방문)
			maxV = 0;  // 맵에서 가장 큰 높이
			ret = 0;   // 최종 등산로 길이
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int value = Integer.parseInt(st.nextToken());
					
					if (value > maxV) maxV = value;  // 맵에서 가장 큰 산의 높이 갱신
					map[r][c] = value;
				}
			}
			
			// 맵에서 가장 큰 산의 높이를 가진 좌표를 기준으로 등산로를 개척한다.
			// 등산로 길이는 방문처리 역할을 하는 visit에서 계산할 수 있다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxV) {
						visit[i][j] = true;
						makeLoad(i, j, 1, 1);
						visit[i][j] = false;
					}
				}
			}
			
			sb.append("#"+t+" "+ret+"\n");
		}
		System.out.println(sb);
	}

	/*
	 *   row: 행 좌표
	 *   col: 열 좌표
	 *   cut: 공사 가능 횟수 (1 or 0)
	 *   cnt: 만들어진 경로 길이
	 * 
	 */
	private static void makeLoad(int row, int col, int cut, int cnt) {		
		//System.out.println(cnt+ " 현재 좌표 = "+ row + " "+col+" "+cut );
		
		// 등산로는 4방향으로 개척할 수 있다 (4-Way delta)
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			if (0<=nx && nx<N && 0<=ny && ny<N && !visit[nx][ny]) {
				
				// 현재 있는 산의 높이가 다음 경로로 지정한 산의 높이보다 크다면 개척 가능.
				if(map[row][col] > map[nx][ny]) {
					// 개척한 곳을 방문처리해서 경로임을 표시한다.
					visit[nx][ny] = true;
					makeLoad(nx, ny, cut, cnt+1);
					visit[nx][ny] = false;	
				}
				
				// 공사가 가능하고(cut==1)
				// 다음 경로로 지정한 산의 높이가 현재 있는 산의 높이보다 커서 공사가 필요할 때,
				// 깎아야 하는 최소 깊이를 K가 충족할 수 있다면 깎아서 처리한다.
				else if (cut==1 && (map[nx][ny]-map[row][col]) < K) {
					visit[nx][ny] = true;
					int tmp = map[nx][ny];
					
					// 1만 차감해도 높이 차가 발생
					// 최대한 적게 차감해야 다른 경로도 탐색할 수 있는 가능성이 높아진다.
					map[nx][ny] = map[row][col]-1; 
					
					// cut(공사 가능)을 0으로 두어 다음 처리에서 공사 못하게 설정
					makeLoad(nx, ny, 0, cnt+1);
	
					// 공사하기 이전 값으로 복원
					map[nx][ny] = tmp;
					visit[nx][ny] = false;
				}
			}	
		}
		// 4방향 탐색을 모두 마무리 했다는 것은 더 이상의 경로가 없음을 의미
		if (cnt > ret) ret = cnt;
	}
}