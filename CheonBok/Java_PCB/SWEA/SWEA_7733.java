import java.io.*;
import java.util.StringTokenizer;
public class SWEA_7733
{
	static boolean[][] remain;   // 일 수마다 새로운 벽이 생기기 때문에 각 경우에 대한 정보를 저장할 배열
	static boolean[][] copy;	 // 치즈 덩어리의 조각(벽) 및 방문 처리를 위한 배열
	static int[][] arr;			 // 원본 배열
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, maxday;
	static int ret;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Tcase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < Tcase+1; t++) {
			N = Integer.parseInt(br.readLine());
			maxday = 0;
			ret = 0;
			int token;
			
			arr = new int[N][N];
			remain = new boolean[N][N];
			copy = new boolean[N][];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					token = Integer.parseInt(st.nextToken());
					arr[r][c] = token;
					
					// 가장 큰 값을 가진 날이 치즈가 모두 사라지는 날 (100일 모두 탐색하지 않기 위함)
					if (maxday < token) maxday = token; 
				}
			}
			
			int size;
			
			// 1일부터 치즈 배열에 벽을 만들기 시작
			for (int i = 1; i < maxday+1; i++) {
				
				// 치즈 배열을 확인해 해당 일(i)과 일치하는 값이면 remain 배열에 벽을 세움(true)
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						// 해당 일의 맛이라면 true로 바꿔 벽을 세운다.
						if (arr[r][c] == i) remain[r][c] = true;
					}
				}
				
				// remain은 day가 지나면서 새로 갱신되어야 하므로 copy로 복사본 사용
				for (int j = 0; j < N; j++) {
					copy[j] = remain[j].clone();
				}
				
				// 출력 테스트용
//				for (boolean[] intt : copy) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(intt[j]+" ");
//					}
//					System.out.println();
//				}
				
				size = 0;
				// 벽이 세워진 상태에서 나눠진 치즈의 조각 개수를 계산 (dfs)
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						// 해당 위치가 벽이 아니라면 = 치즈 구간이므로 bfs로 덩어리 계산
						if (!copy[r][c]) {
							dfs(r, c);
							size++;  // 한 번의 dfs 완료 = 1개의 치즈 덩어리
						}
					}
				}
				
				// size = 0이면 치즈 덩어리 1개 그자체
                if (size == 0) size = 1;
				ret = Math.max(ret, size);
			}
			System.out.println("#"+t+" "+ret);
		}
	}

	private static void dfs(int row, int col) {
		// 인덱스 범위를 벗어나는 경우에는 return
		if (row < 0 || row >= N || col < 0 || col >= N) {
			return;
		}
		
		// 탐색 결과, 해당 위치는 벽(치즈가 아닌)이면 return
		if (copy[row][col]) return;
		
		
		// 해당 조각을 다시 탐색하지 않기 위해 true로 변환
		copy[row][col] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			// 인접 조각이 없어서 return 할 때까지 탐색
			dfs(nx, ny);
		}
	}
}