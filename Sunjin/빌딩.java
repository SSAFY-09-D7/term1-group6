import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			String[][] map = new String[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken();
				}
			}
			
			int result = 0;
			int max = 0; 
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					result = 0;
					int count = 0;
					// 빌딩을 세울 수 있는 구획이라면
					if(map[i][j].equals("B")) {
						
						//8방 탐색
						for(int s = 0; s < 8; s++) {
							int next_row = i + dr[s];
							int next_col = j + dc[s];
							
							// 8방 탐색하는 지점이 알 수 없는 지점이라면
							if(next_row < 0 || next_row >= N || next_col < 0 || next_col >= N) {
								//System.out.println("next2 "+next_row+" "+next_col);
								count++;
								continue;
							}
							
							// 8방 탐색 지점 중 공원이 있다면
							if(map[next_row][next_col].equals("G")) {
								
								result = 2;
								if(max < result) {
									max = result;
								}
								break;
							}
							
							count++;
							
						}
						// 8방 탐색 지점 중 모두가 빌딩부지라면
						if(count == 8) {
							for(int r = 0; r < N; r++) {
								if(map[r][j].equals("B")) {
									result++;
								}
							}
							for(int c = 0; c < N; c++) {
								if(map[i][c].equals("B")) {
									result++;
								}
							}
							result--;
							if(max < result) {
								max = result;
							}
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n", test_case, max);
			
		}
	}	
}
