// SWEA 1861 정사각형 방
import java.io.*;
import java.util.StringTokenizer;
public class SWEA_1861
{
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, ret, tmp;  // NxN 배열의 N^2개 방,  최종 출력,  방 이동 수 임시 저장

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int Tcase = Integer.parseInt(br.readLine());
		for (int t = 1; t < Tcase+1; t++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			ret = N*N+1;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int rooms = 0; // 가장 많은 방을 이동한 수 저장
			// 모든 경우를 확인해야 하며 시작하는 방도 이동 횟수에 포함되므로 tmp = 1
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					tmp = 1;
					checkRoom(r, c); // 방 이동 시작
					
					// 가장 많은 방을 이동한 횟수(rooms)와 같은 결과(tmp)가 나왔다면,
					// 시작 값이 더 작은 방 번호를 반영해야 한다.
					if (rooms == tmp) ret = Math.min(ret, arr[r][c]);
					
					// 가장 많은 이동 수를 갱신하는 경우
					else if (rooms < tmp) {
						rooms = tmp;
						ret = arr[r][c];
					}
				}
			}
			System.out.println("#"+t+" "+ret+" "+rooms);
		}
	}

	
	private static void checkRoom(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			// 이동할 수 있는 방이면 횟수(tmp)를 1 늘리고 다음 방에서 다시 탐색하는 dfs
			if (0<=nx && nx<N && 0<=ny && ny<N && arr[nx][ny] - arr[row][col] == 1) {
				tmp++;
				checkRoom(nx, ny);
			}
		}	
	}
}