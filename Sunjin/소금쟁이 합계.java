import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			int[] jump = {3, 5, 6};
			
			int result = 0;
			
			for(int i = 0; i < count; i++) {
				st = new StringTokenizer(br.readLine());
				int startRow = Integer.parseInt(st.nextToken());
				int startCol = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				// 시작 지점에 다른 소금쟁이가 있다면 죽음
				if(map[startRow][startCol] != 0) {
					continue;
				}
				
				// 위 방향이라면
				if(dir == 1) {
					int cnt = 0;
					// 세번 점프동안 아무도 안 만나야 최종 위치 적는거임
					for(int j = 0; j < jump.length; j++) {
						// 점프 위치가 공간벗어나면
						if(startRow - jump[j] < 0) break;
						
						// 점프 위치에 아무도 없었다면
						if(map[startRow - jump[j]][startCol] == 0) {
							cnt++;
						}
					}
					if(cnt == 3) map[startRow - jump[2]][startCol] = i + 1;
				}
				
				// 아래 방향이라면
				if(dir == 2) {
					int cnt = 0;
					// 세번 점프동안 아무도 안 만나야 최종 위치 적는거임
					for(int j = 0; j < jump.length; j++) {
						// 점프 위치가 공간벗어나면
						if(startRow + jump[j] >= N) break;
						
						// 점프 위치에 아무도 없었다면
						if(map[startRow + jump[j]][startCol] == 0) {
							cnt++;
						}
					}
					if(cnt == 3) map[startRow + jump[2]][startCol] = i + 1;
				}
				
				// 좌측 방향이라면
				if(dir == 3) {
					int cnt = 0;
					// 세번 점프동안 아무도 안 만나야 최종 위치 적는거임
					for(int j = 0; j < jump.length; j++) {
						// 점프 위치가 공간벗어나면
						if(startCol - jump[j] < 0) break;
						
						// 점프 위치에 아무도 없었다면
						if(map[startRow][startCol - jump[j]] == 0) {
							cnt++;
						}
					}
					if(cnt == 3) map[startRow][startCol - jump[2]] = i + 1;
				}
				
				// 우측 방향이라면
				if(dir == 4) {
					int cnt = 0;
					// 세번 점프동안 아무도 안 만나야 최종 위치 적는거임
					for(int j = 0; j < jump.length; j++) {
						// 점프 위치가 공간벗어나면
						if(startCol + jump[j] >= N) break;
						
						// 점프 위치에 아무도 없었다면
						if(map[startRow][startCol + jump[j]] == 0) {
							cnt++;
						}
					}
					if(cnt == 3) map[startRow][startCol + jump[2]] = i + 1;
				}
				
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != 0) {
						result++;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + result);

		}
	}	
}
